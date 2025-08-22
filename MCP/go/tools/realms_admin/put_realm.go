package tools

import (
	"context"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"bytes"

	"github.com/keycloak-admin-rest-api/mcp-server/config"
	"github.com/keycloak-admin-rest-api/mcp-server/models"
	"github.com/mark3labs/mcp-go/mcp"
)

func Put_realmHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		// Create properly typed request body using the generated schema
		var requestBody models.RealmRepresentation
		
		// Optimized: Single marshal/unmarshal with JSON tags handling field mapping
		if argsJSON, err := json.Marshal(args); err == nil {
			if err := json.Unmarshal(argsJSON, &requestBody); err != nil {
				return mcp.NewToolResultError(fmt.Sprintf("Failed to convert arguments to request type: %v", err)), nil
			}
		} else {
			return mcp.NewToolResultError(fmt.Sprintf("Failed to marshal arguments: %v", err)), nil
		}
		
		bodyBytes, err := json.Marshal(requestBody)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to encode request body", err), nil
		}
		url := fmt.Sprintf("%s/%s", cfg.BaseURL)
		req, err := http.NewRequest("PUT", url, bytes.NewBuffer(bodyBytes))
		req.Header.Set("Content-Type", "application/json")
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to create request", err), nil
		}
		// Set authentication based on auth type
		if cfg.BearerToken != "" {
			req.Header.Set("Authorization", fmt.Sprintf("Bearer %s", cfg.BearerToken))
		}
		req.Header.Set("Accept", "application/json")

		resp, err := http.DefaultClient.Do(req)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Request failed", err), nil
		}
		defer resp.Body.Close()

		body, err := io.ReadAll(resp.Body)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to read response body", err), nil
		}

		if resp.StatusCode >= 400 {
			return mcp.NewToolResultError(fmt.Sprintf("API error: %s", body)), nil
		}
		// Use properly typed response
		var result map[string]interface{}
		if err := json.Unmarshal(body, &result); err != nil {
			// Fallback to raw text if unmarshaling fails
			return mcp.NewToolResultText(string(body)), nil
		}

		prettyJSON, err := json.MarshalIndent(result, "", "  ")
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to format JSON", err), nil
		}

		return mcp.NewToolResultText(string(prettyJSON)), nil
	}
}

func CreatePut_realmTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("put_realm",
		mcp.WithDescription("Update the top-level information of the realm   Any user, roles or client information in the representation  will be ignored."),
		mcp.WithString("displayName", mcp.Description("")),
		mcp.WithString("registrationFlow", mcp.Description("")),
		mcp.WithObject("browserSecurityHeaders", mcp.Description("")),
		mcp.WithNumber("offlineSessionMaxLifespan", mcp.Description("")),
		mcp.WithBoolean("verifyEmail", mcp.Description("")),
		mcp.WithBoolean("webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister", mcp.Description("")),
		mcp.WithNumber("accessTokenLifespanForImplicitFlow", mcp.Description("")),
		mcp.WithBoolean("offlineSessionMaxLifespanEnabled", mcp.Description("")),
		mcp.WithString("otpPolicyAlgorithm", mcp.Description("")),
		mcp.WithBoolean("editUsernameAllowed", mcp.Description("")),
		mcp.WithString("loginTheme", mcp.Description("")),
		mcp.WithString("webAuthnPolicyRequireResidentKey", mcp.Description("")),
		mcp.WithArray("otpSupportedApplications", mcp.Description("")),
		mcp.WithArray("requiredActions", mcp.Description("")),
		mcp.WithNumber("refreshTokenMaxReuse", mcp.Description("")),
		mcp.WithBoolean("internationalizationEnabled", mcp.Description("")),
		mcp.WithNumber("clientSessionMaxLifespan", mcp.Description("")),
		mcp.WithString("clientAuthenticationFlow", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessRequireResidentKey", mcp.Description("")),
		mcp.WithArray("supportedLocales", mcp.Description("")),
		mcp.WithArray("clientScopes", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessRpEntityName", mcp.Description("")),
		mcp.WithNumber("accessCodeLifespan", mcp.Description("")),
		mcp.WithString("emailTheme", mcp.Description("")),
		mcp.WithBoolean("resetPasswordAllowed", mcp.Description("")),
		mcp.WithString("browserFlow", mcp.Description("")),
		mcp.WithBoolean("enabled", mcp.Description("")),
		mcp.WithNumber("offlineSessionIdleTimeout", mcp.Description("")),
		mcp.WithBoolean("permanentLockout", mcp.Description("")),
		mcp.WithArray("protocolMappers", mcp.Description("")),
		mcp.WithBoolean("adminEventsDetailsEnabled", mcp.Description("")),
		mcp.WithArray("webAuthnPolicyAcceptableAaguids", mcp.Description("")),
		mcp.WithNumber("accessCodeLifespanUserAction", mcp.Description("")),
		mcp.WithNumber("maxFailureWaitSeconds", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessUserVerificationRequirement", mcp.Description("")),
		mcp.WithString("resetCredentialsFlow", mcp.Description("")),
		mcp.WithArray("authenticationFlows", mcp.Description("")),
		mcp.WithNumber("actionTokenGeneratedByUserLifespan", mcp.Description("")),
		mcp.WithNumber("otpPolicyInitialCounter", mcp.Description("")),
		mcp.WithArray("identityProviderMappers", mcp.Description("")),
		mcp.WithNumber("ssoSessionMaxLifespan", mcp.Description("")),
		mcp.WithNumber("minimumQuickLoginWaitSeconds", mcp.Description("")),
		mcp.WithBoolean("duplicateEmailsAllowed", mcp.Description("")),
		mcp.WithBoolean("registrationAllowed", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessRpId", mcp.Description("")),
		mcp.WithString("accountTheme", mcp.Description("")),
		mcp.WithNumber("webAuthnPolicyCreateTimeout", mcp.Description("")),
		mcp.WithNumber("webAuthnPolicyPasswordlessCreateTimeout", mcp.Description("")),
		mcp.WithBoolean("registrationEmailAsUsername", mcp.Description("")),
		mcp.WithArray("webAuthnPolicyPasswordlessAcceptableAaguids", mcp.Description("")),
		mcp.WithString("sslRequired", mcp.Description("")),
		mcp.WithArray("webAuthnPolicySignatureAlgorithms", mcp.Description("")),
		mcp.WithString("dockerAuthenticationFlow", mcp.Description("")),
		mcp.WithNumber("maxDeltaTimeSeconds", mcp.Description("")),
		mcp.WithString("webAuthnPolicyRpEntityName", mcp.Description("")),
		mcp.WithArray("identityProviders", mcp.Description("")),
		mcp.WithBoolean("revokeRefreshToken", mcp.Description("")),
		mcp.WithArray("eventsListeners", mcp.Description("")),
		mcp.WithArray("users", mcp.Description("")),
		mcp.WithString("defaultLocale", mcp.Description("")),
		mcp.WithObject("clientScopeMappings", mcp.Description("")),
		mcp.WithArray("userFederationMappers", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessAuthenticatorAttachment", mcp.Description("")),
		mcp.WithBoolean("adminEventsEnabled", mcp.Description("")),
		mcp.WithNumber("quickLoginCheckMilliSeconds", mcp.Description("")),
		mcp.WithArray("scopeMappings", mcp.Description("")),
		mcp.WithString("id", mcp.Description("")),
		mcp.WithObject("roles", mcp.Description("")),
		mcp.WithBoolean("webAuthnPolicyAvoidSameAuthenticatorRegister", mcp.Description("")),
		mcp.WithString("webAuthnPolicyAuthenticatorAttachment", mcp.Description("")),
		mcp.WithNumber("accessCodeLifespanLogin", mcp.Description("")),
		mcp.WithNumber("ssoSessionIdleTimeout", mcp.Description("")),
		mcp.WithArray("enabledEventTypes", mcp.Description("")),
		mcp.WithString("keycloakVersion", mcp.Description("")),
		mcp.WithNumber("otpPolicyDigits", mcp.Description("")),
		mcp.WithArray("userFederationProviders", mcp.Description("")),
		mcp.WithArray("defaultRoles", mcp.Description("")),
		mcp.WithBoolean("loginWithEmailAllowed", mcp.Description("")),
		mcp.WithString("adminTheme", mcp.Description("")),
		mcp.WithArray("federatedUsers", mcp.Description("")),
		mcp.WithString("displayNameHtml", mcp.Description("")),
		mcp.WithArray("defaultOptionalClientScopes", mcp.Description("")),
		mcp.WithObject("components", mcp.Description("")),
		mcp.WithArray("groups", mcp.Description("")),
		mcp.WithNumber("otpPolicyLookAheadWindow", mcp.Description("")),
		mcp.WithNumber("accessTokenLifespan", mcp.Description("")),
		mcp.WithString("webAuthnPolicyRpId", mcp.Description("")),
		mcp.WithBoolean("bruteForceProtected", mcp.Description("")),
		mcp.WithNumber("clientSessionIdleTimeout", mcp.Description("")),
		mcp.WithArray("defaultDefaultClientScopes", mcp.Description("")),
		mcp.WithObject("smtpServer", mcp.Description("")),
		mcp.WithObject("attributes", mcp.Description("")),
		mcp.WithNumber("ssoSessionMaxLifespanRememberMe", mcp.Description("")),
		mcp.WithString("realm", mcp.Description("")),
		mcp.WithNumber("otpPolicyPeriod", mcp.Description("")),
		mcp.WithBoolean("eventsEnabled", mcp.Description("")),
		mcp.WithBoolean("userManagedAccessAllowed", mcp.Description("")),
		mcp.WithArray("defaultGroups", mcp.Description("")),
		mcp.WithNumber("actionTokenGeneratedByAdminLifespan", mcp.Description("")),
		mcp.WithNumber("waitIncrementSeconds", mcp.Description("")),
		mcp.WithString("passwordPolicy", mcp.Description("")),
		mcp.WithNumber("notBefore", mcp.Description("")),
		mcp.WithString("webAuthnPolicyPasswordlessAttestationConveyancePreference", mcp.Description("")),
		mcp.WithBoolean("rememberMe", mcp.Description("")),
		mcp.WithString("otpPolicyType", mcp.Description("")),
		mcp.WithArray("clients", mcp.Description("")),
		mcp.WithString("defaultSignatureAlgorithm", mcp.Description("")),
		mcp.WithArray("webAuthnPolicyPasswordlessSignatureAlgorithms", mcp.Description("")),
		mcp.WithNumber("failureFactor", mcp.Description("")),
		mcp.WithNumber("eventsExpiration", mcp.Description("")),
		mcp.WithNumber("ssoSessionIdleTimeoutRememberMe", mcp.Description("")),
		mcp.WithArray("authenticatorConfig", mcp.Description("")),
		mcp.WithString("webAuthnPolicyAttestationConveyancePreference", mcp.Description("")),
		mcp.WithString("directGrantFlow", mcp.Description("")),
		mcp.WithString("webAuthnPolicyUserVerificationRequirement", mcp.Description("")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Put_realmHandler(cfg),
	}
}
