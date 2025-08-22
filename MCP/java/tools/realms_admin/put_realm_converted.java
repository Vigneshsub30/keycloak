/**
 * MCP Server function for Update the top-level information of the realm   Any user, roles or client information in the representation  will be ignored.
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

class Put_RealmMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_RealmHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("displayName")) {
            queryParams.add("displayName=" + args.get("displayName"));
        }
        if (args.containsKey("registrationFlow")) {
            queryParams.add("registrationFlow=" + args.get("registrationFlow"));
        }
        if (args.containsKey("otpPolicyAlgorithm")) {
            queryParams.add("otpPolicyAlgorithm=" + args.get("otpPolicyAlgorithm"));
        }
        if (args.containsKey("loginTheme")) {
            queryParams.add("loginTheme=" + args.get("loginTheme"));
        }
        if (args.containsKey("webAuthnPolicyRequireResidentKey")) {
            queryParams.add("webAuthnPolicyRequireResidentKey=" + args.get("webAuthnPolicyRequireResidentKey"));
        }
        if (args.containsKey("clientAuthenticationFlow")) {
            queryParams.add("clientAuthenticationFlow=" + args.get("clientAuthenticationFlow"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessRequireResidentKey")) {
            queryParams.add("webAuthnPolicyPasswordlessRequireResidentKey=" + args.get("webAuthnPolicyPasswordlessRequireResidentKey"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessRpEntityName")) {
            queryParams.add("webAuthnPolicyPasswordlessRpEntityName=" + args.get("webAuthnPolicyPasswordlessRpEntityName"));
        }
        if (args.containsKey("emailTheme")) {
            queryParams.add("emailTheme=" + args.get("emailTheme"));
        }
        if (args.containsKey("browserFlow")) {
            queryParams.add("browserFlow=" + args.get("browserFlow"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessUserVerificationRequirement")) {
            queryParams.add("webAuthnPolicyPasswordlessUserVerificationRequirement=" + args.get("webAuthnPolicyPasswordlessUserVerificationRequirement"));
        }
        if (args.containsKey("resetCredentialsFlow")) {
            queryParams.add("resetCredentialsFlow=" + args.get("resetCredentialsFlow"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessRpId")) {
            queryParams.add("webAuthnPolicyPasswordlessRpId=" + args.get("webAuthnPolicyPasswordlessRpId"));
        }
        if (args.containsKey("accountTheme")) {
            queryParams.add("accountTheme=" + args.get("accountTheme"));
        }
        if (args.containsKey("sslRequired")) {
            queryParams.add("sslRequired=" + args.get("sslRequired"));
        }
        if (args.containsKey("dockerAuthenticationFlow")) {
            queryParams.add("dockerAuthenticationFlow=" + args.get("dockerAuthenticationFlow"));
        }
        if (args.containsKey("webAuthnPolicyRpEntityName")) {
            queryParams.add("webAuthnPolicyRpEntityName=" + args.get("webAuthnPolicyRpEntityName"));
        }
        if (args.containsKey("defaultLocale")) {
            queryParams.add("defaultLocale=" + args.get("defaultLocale"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessAuthenticatorAttachment")) {
            queryParams.add("webAuthnPolicyPasswordlessAuthenticatorAttachment=" + args.get("webAuthnPolicyPasswordlessAuthenticatorAttachment"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("webAuthnPolicyAuthenticatorAttachment")) {
            queryParams.add("webAuthnPolicyAuthenticatorAttachment=" + args.get("webAuthnPolicyAuthenticatorAttachment"));
        }
        if (args.containsKey("keycloakVersion")) {
            queryParams.add("keycloakVersion=" + args.get("keycloakVersion"));
        }
        if (args.containsKey("adminTheme")) {
            queryParams.add("adminTheme=" + args.get("adminTheme"));
        }
        if (args.containsKey("displayNameHtml")) {
            queryParams.add("displayNameHtml=" + args.get("displayNameHtml"));
        }
        if (args.containsKey("webAuthnPolicyRpId")) {
            queryParams.add("webAuthnPolicyRpId=" + args.get("webAuthnPolicyRpId"));
        }
        if (args.containsKey("realm")) {
            queryParams.add("realm=" + args.get("realm"));
        }
        if (args.containsKey("passwordPolicy")) {
            queryParams.add("passwordPolicy=" + args.get("passwordPolicy"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessAttestationConveyancePreference")) {
            queryParams.add("webAuthnPolicyPasswordlessAttestationConveyancePreference=" + args.get("webAuthnPolicyPasswordlessAttestationConveyancePreference"));
        }
        if (args.containsKey("otpPolicyType")) {
            queryParams.add("otpPolicyType=" + args.get("otpPolicyType"));
        }
        if (args.containsKey("defaultSignatureAlgorithm")) {
            queryParams.add("defaultSignatureAlgorithm=" + args.get("defaultSignatureAlgorithm"));
        }
        if (args.containsKey("webAuthnPolicyAttestationConveyancePreference")) {
            queryParams.add("webAuthnPolicyAttestationConveyancePreference=" + args.get("webAuthnPolicyAttestationConveyancePreference"));
        }
        if (args.containsKey("directGrantFlow")) {
            queryParams.add("directGrantFlow=" + args.get("directGrantFlow"));
        }
        if (args.containsKey("webAuthnPolicyUserVerificationRequirement")) {
            queryParams.add("webAuthnPolicyUserVerificationRequirement=" + args.get("webAuthnPolicyUserVerificationRequirement"));
        }
        if (args.containsKey("offlineSessionMaxLifespan")) {
            queryParams.add("offlineSessionMaxLifespan=" + args.get("offlineSessionMaxLifespan"));
        }
        if (args.containsKey("accessTokenLifespanForImplicitFlow")) {
            queryParams.add("accessTokenLifespanForImplicitFlow=" + args.get("accessTokenLifespanForImplicitFlow"));
        }
        if (args.containsKey("refreshTokenMaxReuse")) {
            queryParams.add("refreshTokenMaxReuse=" + args.get("refreshTokenMaxReuse"));
        }
        if (args.containsKey("clientSessionMaxLifespan")) {
            queryParams.add("clientSessionMaxLifespan=" + args.get("clientSessionMaxLifespan"));
        }
        if (args.containsKey("accessCodeLifespan")) {
            queryParams.add("accessCodeLifespan=" + args.get("accessCodeLifespan"));
        }
        if (args.containsKey("offlineSessionIdleTimeout")) {
            queryParams.add("offlineSessionIdleTimeout=" + args.get("offlineSessionIdleTimeout"));
        }
        if (args.containsKey("accessCodeLifespanUserAction")) {
            queryParams.add("accessCodeLifespanUserAction=" + args.get("accessCodeLifespanUserAction"));
        }
        if (args.containsKey("maxFailureWaitSeconds")) {
            queryParams.add("maxFailureWaitSeconds=" + args.get("maxFailureWaitSeconds"));
        }
        if (args.containsKey("actionTokenGeneratedByUserLifespan")) {
            queryParams.add("actionTokenGeneratedByUserLifespan=" + args.get("actionTokenGeneratedByUserLifespan"));
        }
        if (args.containsKey("otpPolicyInitialCounter")) {
            queryParams.add("otpPolicyInitialCounter=" + args.get("otpPolicyInitialCounter"));
        }
        if (args.containsKey("ssoSessionMaxLifespan")) {
            queryParams.add("ssoSessionMaxLifespan=" + args.get("ssoSessionMaxLifespan"));
        }
        if (args.containsKey("minimumQuickLoginWaitSeconds")) {
            queryParams.add("minimumQuickLoginWaitSeconds=" + args.get("minimumQuickLoginWaitSeconds"));
        }
        if (args.containsKey("webAuthnPolicyCreateTimeout")) {
            queryParams.add("webAuthnPolicyCreateTimeout=" + args.get("webAuthnPolicyCreateTimeout"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessCreateTimeout")) {
            queryParams.add("webAuthnPolicyPasswordlessCreateTimeout=" + args.get("webAuthnPolicyPasswordlessCreateTimeout"));
        }
        if (args.containsKey("maxDeltaTimeSeconds")) {
            queryParams.add("maxDeltaTimeSeconds=" + args.get("maxDeltaTimeSeconds"));
        }
        if (args.containsKey("quickLoginCheckMilliSeconds")) {
            queryParams.add("quickLoginCheckMilliSeconds=" + args.get("quickLoginCheckMilliSeconds"));
        }
        if (args.containsKey("accessCodeLifespanLogin")) {
            queryParams.add("accessCodeLifespanLogin=" + args.get("accessCodeLifespanLogin"));
        }
        if (args.containsKey("ssoSessionIdleTimeout")) {
            queryParams.add("ssoSessionIdleTimeout=" + args.get("ssoSessionIdleTimeout"));
        }
        if (args.containsKey("otpPolicyDigits")) {
            queryParams.add("otpPolicyDigits=" + args.get("otpPolicyDigits"));
        }
        if (args.containsKey("otpPolicyLookAheadWindow")) {
            queryParams.add("otpPolicyLookAheadWindow=" + args.get("otpPolicyLookAheadWindow"));
        }
        if (args.containsKey("accessTokenLifespan")) {
            queryParams.add("accessTokenLifespan=" + args.get("accessTokenLifespan"));
        }
        if (args.containsKey("clientSessionIdleTimeout")) {
            queryParams.add("clientSessionIdleTimeout=" + args.get("clientSessionIdleTimeout"));
        }
        if (args.containsKey("ssoSessionMaxLifespanRememberMe")) {
            queryParams.add("ssoSessionMaxLifespanRememberMe=" + args.get("ssoSessionMaxLifespanRememberMe"));
        }
        if (args.containsKey("otpPolicyPeriod")) {
            queryParams.add("otpPolicyPeriod=" + args.get("otpPolicyPeriod"));
        }
        if (args.containsKey("actionTokenGeneratedByAdminLifespan")) {
            queryParams.add("actionTokenGeneratedByAdminLifespan=" + args.get("actionTokenGeneratedByAdminLifespan"));
        }
        if (args.containsKey("waitIncrementSeconds")) {
            queryParams.add("waitIncrementSeconds=" + args.get("waitIncrementSeconds"));
        }
        if (args.containsKey("notBefore")) {
            queryParams.add("notBefore=" + args.get("notBefore"));
        }
        if (args.containsKey("failureFactor")) {
            queryParams.add("failureFactor=" + args.get("failureFactor"));
        }
        if (args.containsKey("eventsExpiration")) {
            queryParams.add("eventsExpiration=" + args.get("eventsExpiration"));
        }
        if (args.containsKey("ssoSessionIdleTimeoutRememberMe")) {
            queryParams.add("ssoSessionIdleTimeoutRememberMe=" + args.get("ssoSessionIdleTimeoutRememberMe"));
        }
        if (args.containsKey("verifyEmail")) {
            queryParams.add("verifyEmail=" + args.get("verifyEmail"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister")) {
            queryParams.add("webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister=" + args.get("webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister"));
        }
        if (args.containsKey("offlineSessionMaxLifespanEnabled")) {
            queryParams.add("offlineSessionMaxLifespanEnabled=" + args.get("offlineSessionMaxLifespanEnabled"));
        }
        if (args.containsKey("editUsernameAllowed")) {
            queryParams.add("editUsernameAllowed=" + args.get("editUsernameAllowed"));
        }
        if (args.containsKey("internationalizationEnabled")) {
            queryParams.add("internationalizationEnabled=" + args.get("internationalizationEnabled"));
        }
        if (args.containsKey("resetPasswordAllowed")) {
            queryParams.add("resetPasswordAllowed=" + args.get("resetPasswordAllowed"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("permanentLockout")) {
            queryParams.add("permanentLockout=" + args.get("permanentLockout"));
        }
        if (args.containsKey("adminEventsDetailsEnabled")) {
            queryParams.add("adminEventsDetailsEnabled=" + args.get("adminEventsDetailsEnabled"));
        }
        if (args.containsKey("duplicateEmailsAllowed")) {
            queryParams.add("duplicateEmailsAllowed=" + args.get("duplicateEmailsAllowed"));
        }
        if (args.containsKey("registrationAllowed")) {
            queryParams.add("registrationAllowed=" + args.get("registrationAllowed"));
        }
        if (args.containsKey("registrationEmailAsUsername")) {
            queryParams.add("registrationEmailAsUsername=" + args.get("registrationEmailAsUsername"));
        }
        if (args.containsKey("revokeRefreshToken")) {
            queryParams.add("revokeRefreshToken=" + args.get("revokeRefreshToken"));
        }
        if (args.containsKey("adminEventsEnabled")) {
            queryParams.add("adminEventsEnabled=" + args.get("adminEventsEnabled"));
        }
        if (args.containsKey("webAuthnPolicyAvoidSameAuthenticatorRegister")) {
            queryParams.add("webAuthnPolicyAvoidSameAuthenticatorRegister=" + args.get("webAuthnPolicyAvoidSameAuthenticatorRegister"));
        }
        if (args.containsKey("loginWithEmailAllowed")) {
            queryParams.add("loginWithEmailAllowed=" + args.get("loginWithEmailAllowed"));
        }
        if (args.containsKey("bruteForceProtected")) {
            queryParams.add("bruteForceProtected=" + args.get("bruteForceProtected"));
        }
        if (args.containsKey("eventsEnabled")) {
            queryParams.add("eventsEnabled=" + args.get("eventsEnabled"));
        }
        if (args.containsKey("userManagedAccessAllowed")) {
            queryParams.add("userManagedAccessAllowed=" + args.get("userManagedAccessAllowed"));
        }
        if (args.containsKey("rememberMe")) {
            queryParams.add("rememberMe=" + args.get("rememberMe"));
        }
        if (args.containsKey("browserSecurityHeaders")) {
            queryParams.add("browserSecurityHeaders=" + args.get("browserSecurityHeaders"));
        }
        if (args.containsKey("clientScopeMappings")) {
            queryParams.add("clientScopeMappings=" + args.get("clientScopeMappings"));
        }
        if (args.containsKey("roles")) {
            queryParams.add("roles=" + args.get("roles"));
        }
        if (args.containsKey("components")) {
            queryParams.add("components=" + args.get("components"));
        }
        if (args.containsKey("smtpServer")) {
            queryParams.add("smtpServer=" + args.get("smtpServer"));
        }
        if (args.containsKey("attributes")) {
            queryParams.add("attributes=" + args.get("attributes"));
        }
        if (args.containsKey("otpSupportedApplications")) {
            queryParams.add("otpSupportedApplications=" + args.get("otpSupportedApplications"));
        }
        if (args.containsKey("requiredActions")) {
            queryParams.add("requiredActions=" + args.get("requiredActions"));
        }
        if (args.containsKey("supportedLocales")) {
            queryParams.add("supportedLocales=" + args.get("supportedLocales"));
        }
        if (args.containsKey("clientScopes")) {
            queryParams.add("clientScopes=" + args.get("clientScopes"));
        }
        if (args.containsKey("protocolMappers")) {
            queryParams.add("protocolMappers=" + args.get("protocolMappers"));
        }
        if (args.containsKey("webAuthnPolicyAcceptableAaguids")) {
            queryParams.add("webAuthnPolicyAcceptableAaguids=" + args.get("webAuthnPolicyAcceptableAaguids"));
        }
        if (args.containsKey("authenticationFlows")) {
            queryParams.add("authenticationFlows=" + args.get("authenticationFlows"));
        }
        if (args.containsKey("identityProviderMappers")) {
            queryParams.add("identityProviderMappers=" + args.get("identityProviderMappers"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessAcceptableAaguids")) {
            queryParams.add("webAuthnPolicyPasswordlessAcceptableAaguids=" + args.get("webAuthnPolicyPasswordlessAcceptableAaguids"));
        }
        if (args.containsKey("webAuthnPolicySignatureAlgorithms")) {
            queryParams.add("webAuthnPolicySignatureAlgorithms=" + args.get("webAuthnPolicySignatureAlgorithms"));
        }
        if (args.containsKey("identityProviders")) {
            queryParams.add("identityProviders=" + args.get("identityProviders"));
        }
        if (args.containsKey("eventsListeners")) {
            queryParams.add("eventsListeners=" + args.get("eventsListeners"));
        }
        if (args.containsKey("users")) {
            queryParams.add("users=" + args.get("users"));
        }
        if (args.containsKey("userFederationMappers")) {
            queryParams.add("userFederationMappers=" + args.get("userFederationMappers"));
        }
        if (args.containsKey("scopeMappings")) {
            queryParams.add("scopeMappings=" + args.get("scopeMappings"));
        }
        if (args.containsKey("enabledEventTypes")) {
            queryParams.add("enabledEventTypes=" + args.get("enabledEventTypes"));
        }
        if (args.containsKey("userFederationProviders")) {
            queryParams.add("userFederationProviders=" + args.get("userFederationProviders"));
        }
        if (args.containsKey("defaultRoles")) {
            queryParams.add("defaultRoles=" + args.get("defaultRoles"));
        }
        if (args.containsKey("federatedUsers")) {
            queryParams.add("federatedUsers=" + args.get("federatedUsers"));
        }
        if (args.containsKey("defaultOptionalClientScopes")) {
            queryParams.add("defaultOptionalClientScopes=" + args.get("defaultOptionalClientScopes"));
        }
        if (args.containsKey("groups")) {
            queryParams.add("groups=" + args.get("groups"));
        }
        if (args.containsKey("defaultDefaultClientScopes")) {
            queryParams.add("defaultDefaultClientScopes=" + args.get("defaultDefaultClientScopes"));
        }
        if (args.containsKey("defaultGroups")) {
            queryParams.add("defaultGroups=" + args.get("defaultGroups"));
        }
        if (args.containsKey("clients")) {
            queryParams.add("clients=" + args.get("clients"));
        }
        if (args.containsKey("webAuthnPolicyPasswordlessSignatureAlgorithms")) {
            queryParams.add("webAuthnPolicyPasswordlessSignatureAlgorithms=" + args.get("webAuthnPolicyPasswordlessSignatureAlgorithms"));
        }
        if (args.containsKey("authenticatorConfig")) {
            queryParams.add("authenticatorConfig=" + args.get("authenticatorConfig"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm" + queryString;
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + config.getApiKey())
                    .header("Accept", "application/json")
                    .GET()
                    .build();
                
                HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() >= 400) {
                    return new MCPServer.MCPToolResult("API error: " + response.body(), true);
                }
                
                // Pretty print JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.body());
                String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                
                return new MCPServer.MCPToolResult(prettyJson);
                
            } catch (IOException | InterruptedException e) {
                return new MCPServer.MCPToolResult("Request failed: " + e.getMessage(), true);
            } catch (Exception e) {
                return new MCPServer.MCPToolResult("Unexpected error: " + e.getMessage(), true);
            }
        };
    }
    
    public static MCPServer.Tool createPut_RealmTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> displayNameProperty = new HashMap<>();
        displayNameProperty.put("type", "string");
        displayNameProperty.put("required", false);
        displayNameProperty.put("description", "");
        properties.put("displayName", displayNameProperty);
        Map<String, Object> registrationFlowProperty = new HashMap<>();
        registrationFlowProperty.put("type", "string");
        registrationFlowProperty.put("required", false);
        registrationFlowProperty.put("description", "");
        properties.put("registrationFlow", registrationFlowProperty);
        Map<String, Object> otpPolicyAlgorithmProperty = new HashMap<>();
        otpPolicyAlgorithmProperty.put("type", "string");
        otpPolicyAlgorithmProperty.put("required", false);
        otpPolicyAlgorithmProperty.put("description", "");
        properties.put("otpPolicyAlgorithm", otpPolicyAlgorithmProperty);
        Map<String, Object> loginThemeProperty = new HashMap<>();
        loginThemeProperty.put("type", "string");
        loginThemeProperty.put("required", false);
        loginThemeProperty.put("description", "");
        properties.put("loginTheme", loginThemeProperty);
        Map<String, Object> webAuthnPolicyRequireResidentKeyProperty = new HashMap<>();
        webAuthnPolicyRequireResidentKeyProperty.put("type", "string");
        webAuthnPolicyRequireResidentKeyProperty.put("required", false);
        webAuthnPolicyRequireResidentKeyProperty.put("description", "");
        properties.put("webAuthnPolicyRequireResidentKey", webAuthnPolicyRequireResidentKeyProperty);
        Map<String, Object> clientAuthenticationFlowProperty = new HashMap<>();
        clientAuthenticationFlowProperty.put("type", "string");
        clientAuthenticationFlowProperty.put("required", false);
        clientAuthenticationFlowProperty.put("description", "");
        properties.put("clientAuthenticationFlow", clientAuthenticationFlowProperty);
        Map<String, Object> webAuthnPolicyPasswordlessRequireResidentKeyProperty = new HashMap<>();
        webAuthnPolicyPasswordlessRequireResidentKeyProperty.put("type", "string");
        webAuthnPolicyPasswordlessRequireResidentKeyProperty.put("required", false);
        webAuthnPolicyPasswordlessRequireResidentKeyProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessRequireResidentKey", webAuthnPolicyPasswordlessRequireResidentKeyProperty);
        Map<String, Object> webAuthnPolicyPasswordlessRpEntityNameProperty = new HashMap<>();
        webAuthnPolicyPasswordlessRpEntityNameProperty.put("type", "string");
        webAuthnPolicyPasswordlessRpEntityNameProperty.put("required", false);
        webAuthnPolicyPasswordlessRpEntityNameProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessRpEntityName", webAuthnPolicyPasswordlessRpEntityNameProperty);
        Map<String, Object> emailThemeProperty = new HashMap<>();
        emailThemeProperty.put("type", "string");
        emailThemeProperty.put("required", false);
        emailThemeProperty.put("description", "");
        properties.put("emailTheme", emailThemeProperty);
        Map<String, Object> browserFlowProperty = new HashMap<>();
        browserFlowProperty.put("type", "string");
        browserFlowProperty.put("required", false);
        browserFlowProperty.put("description", "");
        properties.put("browserFlow", browserFlowProperty);
        Map<String, Object> webAuthnPolicyPasswordlessUserVerificationRequirementProperty = new HashMap<>();
        webAuthnPolicyPasswordlessUserVerificationRequirementProperty.put("type", "string");
        webAuthnPolicyPasswordlessUserVerificationRequirementProperty.put("required", false);
        webAuthnPolicyPasswordlessUserVerificationRequirementProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessUserVerificationRequirement", webAuthnPolicyPasswordlessUserVerificationRequirementProperty);
        Map<String, Object> resetCredentialsFlowProperty = new HashMap<>();
        resetCredentialsFlowProperty.put("type", "string");
        resetCredentialsFlowProperty.put("required", false);
        resetCredentialsFlowProperty.put("description", "");
        properties.put("resetCredentialsFlow", resetCredentialsFlowProperty);
        Map<String, Object> webAuthnPolicyPasswordlessRpIdProperty = new HashMap<>();
        webAuthnPolicyPasswordlessRpIdProperty.put("type", "string");
        webAuthnPolicyPasswordlessRpIdProperty.put("required", false);
        webAuthnPolicyPasswordlessRpIdProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessRpId", webAuthnPolicyPasswordlessRpIdProperty);
        Map<String, Object> accountThemeProperty = new HashMap<>();
        accountThemeProperty.put("type", "string");
        accountThemeProperty.put("required", false);
        accountThemeProperty.put("description", "");
        properties.put("accountTheme", accountThemeProperty);
        Map<String, Object> sslRequiredProperty = new HashMap<>();
        sslRequiredProperty.put("type", "string");
        sslRequiredProperty.put("required", false);
        sslRequiredProperty.put("description", "");
        properties.put("sslRequired", sslRequiredProperty);
        Map<String, Object> dockerAuthenticationFlowProperty = new HashMap<>();
        dockerAuthenticationFlowProperty.put("type", "string");
        dockerAuthenticationFlowProperty.put("required", false);
        dockerAuthenticationFlowProperty.put("description", "");
        properties.put("dockerAuthenticationFlow", dockerAuthenticationFlowProperty);
        Map<String, Object> webAuthnPolicyRpEntityNameProperty = new HashMap<>();
        webAuthnPolicyRpEntityNameProperty.put("type", "string");
        webAuthnPolicyRpEntityNameProperty.put("required", false);
        webAuthnPolicyRpEntityNameProperty.put("description", "");
        properties.put("webAuthnPolicyRpEntityName", webAuthnPolicyRpEntityNameProperty);
        Map<String, Object> defaultLocaleProperty = new HashMap<>();
        defaultLocaleProperty.put("type", "string");
        defaultLocaleProperty.put("required", false);
        defaultLocaleProperty.put("description", "");
        properties.put("defaultLocale", defaultLocaleProperty);
        Map<String, Object> webAuthnPolicyPasswordlessAuthenticatorAttachmentProperty = new HashMap<>();
        webAuthnPolicyPasswordlessAuthenticatorAttachmentProperty.put("type", "string");
        webAuthnPolicyPasswordlessAuthenticatorAttachmentProperty.put("required", false);
        webAuthnPolicyPasswordlessAuthenticatorAttachmentProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessAuthenticatorAttachment", webAuthnPolicyPasswordlessAuthenticatorAttachmentProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> webAuthnPolicyAuthenticatorAttachmentProperty = new HashMap<>();
        webAuthnPolicyAuthenticatorAttachmentProperty.put("type", "string");
        webAuthnPolicyAuthenticatorAttachmentProperty.put("required", false);
        webAuthnPolicyAuthenticatorAttachmentProperty.put("description", "");
        properties.put("webAuthnPolicyAuthenticatorAttachment", webAuthnPolicyAuthenticatorAttachmentProperty);
        Map<String, Object> keycloakVersionProperty = new HashMap<>();
        keycloakVersionProperty.put("type", "string");
        keycloakVersionProperty.put("required", false);
        keycloakVersionProperty.put("description", "");
        properties.put("keycloakVersion", keycloakVersionProperty);
        Map<String, Object> adminThemeProperty = new HashMap<>();
        adminThemeProperty.put("type", "string");
        adminThemeProperty.put("required", false);
        adminThemeProperty.put("description", "");
        properties.put("adminTheme", adminThemeProperty);
        Map<String, Object> displayNameHtmlProperty = new HashMap<>();
        displayNameHtmlProperty.put("type", "string");
        displayNameHtmlProperty.put("required", false);
        displayNameHtmlProperty.put("description", "");
        properties.put("displayNameHtml", displayNameHtmlProperty);
        Map<String, Object> webAuthnPolicyRpIdProperty = new HashMap<>();
        webAuthnPolicyRpIdProperty.put("type", "string");
        webAuthnPolicyRpIdProperty.put("required", false);
        webAuthnPolicyRpIdProperty.put("description", "");
        properties.put("webAuthnPolicyRpId", webAuthnPolicyRpIdProperty);
        Map<String, Object> realmProperty = new HashMap<>();
        realmProperty.put("type", "string");
        realmProperty.put("required", false);
        realmProperty.put("description", "");
        properties.put("realm", realmProperty);
        Map<String, Object> passwordPolicyProperty = new HashMap<>();
        passwordPolicyProperty.put("type", "string");
        passwordPolicyProperty.put("required", false);
        passwordPolicyProperty.put("description", "");
        properties.put("passwordPolicy", passwordPolicyProperty);
        Map<String, Object> webAuthnPolicyPasswordlessAttestationConveyancePreferenceProperty = new HashMap<>();
        webAuthnPolicyPasswordlessAttestationConveyancePreferenceProperty.put("type", "string");
        webAuthnPolicyPasswordlessAttestationConveyancePreferenceProperty.put("required", false);
        webAuthnPolicyPasswordlessAttestationConveyancePreferenceProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessAttestationConveyancePreference", webAuthnPolicyPasswordlessAttestationConveyancePreferenceProperty);
        Map<String, Object> otpPolicyTypeProperty = new HashMap<>();
        otpPolicyTypeProperty.put("type", "string");
        otpPolicyTypeProperty.put("required", false);
        otpPolicyTypeProperty.put("description", "");
        properties.put("otpPolicyType", otpPolicyTypeProperty);
        Map<String, Object> defaultSignatureAlgorithmProperty = new HashMap<>();
        defaultSignatureAlgorithmProperty.put("type", "string");
        defaultSignatureAlgorithmProperty.put("required", false);
        defaultSignatureAlgorithmProperty.put("description", "");
        properties.put("defaultSignatureAlgorithm", defaultSignatureAlgorithmProperty);
        Map<String, Object> webAuthnPolicyAttestationConveyancePreferenceProperty = new HashMap<>();
        webAuthnPolicyAttestationConveyancePreferenceProperty.put("type", "string");
        webAuthnPolicyAttestationConveyancePreferenceProperty.put("required", false);
        webAuthnPolicyAttestationConveyancePreferenceProperty.put("description", "");
        properties.put("webAuthnPolicyAttestationConveyancePreference", webAuthnPolicyAttestationConveyancePreferenceProperty);
        Map<String, Object> directGrantFlowProperty = new HashMap<>();
        directGrantFlowProperty.put("type", "string");
        directGrantFlowProperty.put("required", false);
        directGrantFlowProperty.put("description", "");
        properties.put("directGrantFlow", directGrantFlowProperty);
        Map<String, Object> webAuthnPolicyUserVerificationRequirementProperty = new HashMap<>();
        webAuthnPolicyUserVerificationRequirementProperty.put("type", "string");
        webAuthnPolicyUserVerificationRequirementProperty.put("required", false);
        webAuthnPolicyUserVerificationRequirementProperty.put("description", "");
        properties.put("webAuthnPolicyUserVerificationRequirement", webAuthnPolicyUserVerificationRequirementProperty);
        Map<String, Object> offlineSessionMaxLifespanProperty = new HashMap<>();
        offlineSessionMaxLifespanProperty.put("type", "string");
        offlineSessionMaxLifespanProperty.put("required", false);
        offlineSessionMaxLifespanProperty.put("description", "");
        properties.put("offlineSessionMaxLifespan", offlineSessionMaxLifespanProperty);
        Map<String, Object> accessTokenLifespanForImplicitFlowProperty = new HashMap<>();
        accessTokenLifespanForImplicitFlowProperty.put("type", "string");
        accessTokenLifespanForImplicitFlowProperty.put("required", false);
        accessTokenLifespanForImplicitFlowProperty.put("description", "");
        properties.put("accessTokenLifespanForImplicitFlow", accessTokenLifespanForImplicitFlowProperty);
        Map<String, Object> refreshTokenMaxReuseProperty = new HashMap<>();
        refreshTokenMaxReuseProperty.put("type", "string");
        refreshTokenMaxReuseProperty.put("required", false);
        refreshTokenMaxReuseProperty.put("description", "");
        properties.put("refreshTokenMaxReuse", refreshTokenMaxReuseProperty);
        Map<String, Object> clientSessionMaxLifespanProperty = new HashMap<>();
        clientSessionMaxLifespanProperty.put("type", "string");
        clientSessionMaxLifespanProperty.put("required", false);
        clientSessionMaxLifespanProperty.put("description", "");
        properties.put("clientSessionMaxLifespan", clientSessionMaxLifespanProperty);
        Map<String, Object> accessCodeLifespanProperty = new HashMap<>();
        accessCodeLifespanProperty.put("type", "string");
        accessCodeLifespanProperty.put("required", false);
        accessCodeLifespanProperty.put("description", "");
        properties.put("accessCodeLifespan", accessCodeLifespanProperty);
        Map<String, Object> offlineSessionIdleTimeoutProperty = new HashMap<>();
        offlineSessionIdleTimeoutProperty.put("type", "string");
        offlineSessionIdleTimeoutProperty.put("required", false);
        offlineSessionIdleTimeoutProperty.put("description", "");
        properties.put("offlineSessionIdleTimeout", offlineSessionIdleTimeoutProperty);
        Map<String, Object> accessCodeLifespanUserActionProperty = new HashMap<>();
        accessCodeLifespanUserActionProperty.put("type", "string");
        accessCodeLifespanUserActionProperty.put("required", false);
        accessCodeLifespanUserActionProperty.put("description", "");
        properties.put("accessCodeLifespanUserAction", accessCodeLifespanUserActionProperty);
        Map<String, Object> maxFailureWaitSecondsProperty = new HashMap<>();
        maxFailureWaitSecondsProperty.put("type", "string");
        maxFailureWaitSecondsProperty.put("required", false);
        maxFailureWaitSecondsProperty.put("description", "");
        properties.put("maxFailureWaitSeconds", maxFailureWaitSecondsProperty);
        Map<String, Object> actionTokenGeneratedByUserLifespanProperty = new HashMap<>();
        actionTokenGeneratedByUserLifespanProperty.put("type", "string");
        actionTokenGeneratedByUserLifespanProperty.put("required", false);
        actionTokenGeneratedByUserLifespanProperty.put("description", "");
        properties.put("actionTokenGeneratedByUserLifespan", actionTokenGeneratedByUserLifespanProperty);
        Map<String, Object> otpPolicyInitialCounterProperty = new HashMap<>();
        otpPolicyInitialCounterProperty.put("type", "string");
        otpPolicyInitialCounterProperty.put("required", false);
        otpPolicyInitialCounterProperty.put("description", "");
        properties.put("otpPolicyInitialCounter", otpPolicyInitialCounterProperty);
        Map<String, Object> ssoSessionMaxLifespanProperty = new HashMap<>();
        ssoSessionMaxLifespanProperty.put("type", "string");
        ssoSessionMaxLifespanProperty.put("required", false);
        ssoSessionMaxLifespanProperty.put("description", "");
        properties.put("ssoSessionMaxLifespan", ssoSessionMaxLifespanProperty);
        Map<String, Object> minimumQuickLoginWaitSecondsProperty = new HashMap<>();
        minimumQuickLoginWaitSecondsProperty.put("type", "string");
        minimumQuickLoginWaitSecondsProperty.put("required", false);
        minimumQuickLoginWaitSecondsProperty.put("description", "");
        properties.put("minimumQuickLoginWaitSeconds", minimumQuickLoginWaitSecondsProperty);
        Map<String, Object> webAuthnPolicyCreateTimeoutProperty = new HashMap<>();
        webAuthnPolicyCreateTimeoutProperty.put("type", "string");
        webAuthnPolicyCreateTimeoutProperty.put("required", false);
        webAuthnPolicyCreateTimeoutProperty.put("description", "");
        properties.put("webAuthnPolicyCreateTimeout", webAuthnPolicyCreateTimeoutProperty);
        Map<String, Object> webAuthnPolicyPasswordlessCreateTimeoutProperty = new HashMap<>();
        webAuthnPolicyPasswordlessCreateTimeoutProperty.put("type", "string");
        webAuthnPolicyPasswordlessCreateTimeoutProperty.put("required", false);
        webAuthnPolicyPasswordlessCreateTimeoutProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessCreateTimeout", webAuthnPolicyPasswordlessCreateTimeoutProperty);
        Map<String, Object> maxDeltaTimeSecondsProperty = new HashMap<>();
        maxDeltaTimeSecondsProperty.put("type", "string");
        maxDeltaTimeSecondsProperty.put("required", false);
        maxDeltaTimeSecondsProperty.put("description", "");
        properties.put("maxDeltaTimeSeconds", maxDeltaTimeSecondsProperty);
        Map<String, Object> quickLoginCheckMilliSecondsProperty = new HashMap<>();
        quickLoginCheckMilliSecondsProperty.put("type", "string");
        quickLoginCheckMilliSecondsProperty.put("required", false);
        quickLoginCheckMilliSecondsProperty.put("description", "");
        properties.put("quickLoginCheckMilliSeconds", quickLoginCheckMilliSecondsProperty);
        Map<String, Object> accessCodeLifespanLoginProperty = new HashMap<>();
        accessCodeLifespanLoginProperty.put("type", "string");
        accessCodeLifespanLoginProperty.put("required", false);
        accessCodeLifespanLoginProperty.put("description", "");
        properties.put("accessCodeLifespanLogin", accessCodeLifespanLoginProperty);
        Map<String, Object> ssoSessionIdleTimeoutProperty = new HashMap<>();
        ssoSessionIdleTimeoutProperty.put("type", "string");
        ssoSessionIdleTimeoutProperty.put("required", false);
        ssoSessionIdleTimeoutProperty.put("description", "");
        properties.put("ssoSessionIdleTimeout", ssoSessionIdleTimeoutProperty);
        Map<String, Object> otpPolicyDigitsProperty = new HashMap<>();
        otpPolicyDigitsProperty.put("type", "string");
        otpPolicyDigitsProperty.put("required", false);
        otpPolicyDigitsProperty.put("description", "");
        properties.put("otpPolicyDigits", otpPolicyDigitsProperty);
        Map<String, Object> otpPolicyLookAheadWindowProperty = new HashMap<>();
        otpPolicyLookAheadWindowProperty.put("type", "string");
        otpPolicyLookAheadWindowProperty.put("required", false);
        otpPolicyLookAheadWindowProperty.put("description", "");
        properties.put("otpPolicyLookAheadWindow", otpPolicyLookAheadWindowProperty);
        Map<String, Object> accessTokenLifespanProperty = new HashMap<>();
        accessTokenLifespanProperty.put("type", "string");
        accessTokenLifespanProperty.put("required", false);
        accessTokenLifespanProperty.put("description", "");
        properties.put("accessTokenLifespan", accessTokenLifespanProperty);
        Map<String, Object> clientSessionIdleTimeoutProperty = new HashMap<>();
        clientSessionIdleTimeoutProperty.put("type", "string");
        clientSessionIdleTimeoutProperty.put("required", false);
        clientSessionIdleTimeoutProperty.put("description", "");
        properties.put("clientSessionIdleTimeout", clientSessionIdleTimeoutProperty);
        Map<String, Object> ssoSessionMaxLifespanRememberMeProperty = new HashMap<>();
        ssoSessionMaxLifespanRememberMeProperty.put("type", "string");
        ssoSessionMaxLifespanRememberMeProperty.put("required", false);
        ssoSessionMaxLifespanRememberMeProperty.put("description", "");
        properties.put("ssoSessionMaxLifespanRememberMe", ssoSessionMaxLifespanRememberMeProperty);
        Map<String, Object> otpPolicyPeriodProperty = new HashMap<>();
        otpPolicyPeriodProperty.put("type", "string");
        otpPolicyPeriodProperty.put("required", false);
        otpPolicyPeriodProperty.put("description", "");
        properties.put("otpPolicyPeriod", otpPolicyPeriodProperty);
        Map<String, Object> actionTokenGeneratedByAdminLifespanProperty = new HashMap<>();
        actionTokenGeneratedByAdminLifespanProperty.put("type", "string");
        actionTokenGeneratedByAdminLifespanProperty.put("required", false);
        actionTokenGeneratedByAdminLifespanProperty.put("description", "");
        properties.put("actionTokenGeneratedByAdminLifespan", actionTokenGeneratedByAdminLifespanProperty);
        Map<String, Object> waitIncrementSecondsProperty = new HashMap<>();
        waitIncrementSecondsProperty.put("type", "string");
        waitIncrementSecondsProperty.put("required", false);
        waitIncrementSecondsProperty.put("description", "");
        properties.put("waitIncrementSeconds", waitIncrementSecondsProperty);
        Map<String, Object> notBeforeProperty = new HashMap<>();
        notBeforeProperty.put("type", "string");
        notBeforeProperty.put("required", false);
        notBeforeProperty.put("description", "");
        properties.put("notBefore", notBeforeProperty);
        Map<String, Object> failureFactorProperty = new HashMap<>();
        failureFactorProperty.put("type", "string");
        failureFactorProperty.put("required", false);
        failureFactorProperty.put("description", "");
        properties.put("failureFactor", failureFactorProperty);
        Map<String, Object> eventsExpirationProperty = new HashMap<>();
        eventsExpirationProperty.put("type", "string");
        eventsExpirationProperty.put("required", false);
        eventsExpirationProperty.put("description", "");
        properties.put("eventsExpiration", eventsExpirationProperty);
        Map<String, Object> ssoSessionIdleTimeoutRememberMeProperty = new HashMap<>();
        ssoSessionIdleTimeoutRememberMeProperty.put("type", "string");
        ssoSessionIdleTimeoutRememberMeProperty.put("required", false);
        ssoSessionIdleTimeoutRememberMeProperty.put("description", "");
        properties.put("ssoSessionIdleTimeoutRememberMe", ssoSessionIdleTimeoutRememberMeProperty);
        Map<String, Object> verifyEmailProperty = new HashMap<>();
        verifyEmailProperty.put("type", "string");
        verifyEmailProperty.put("required", false);
        verifyEmailProperty.put("description", "");
        properties.put("verifyEmail", verifyEmailProperty);
        Map<String, Object> webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegisterProperty = new HashMap<>();
        webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegisterProperty.put("type", "string");
        webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegisterProperty.put("required", false);
        webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegisterProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister", webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegisterProperty);
        Map<String, Object> offlineSessionMaxLifespanEnabledProperty = new HashMap<>();
        offlineSessionMaxLifespanEnabledProperty.put("type", "string");
        offlineSessionMaxLifespanEnabledProperty.put("required", false);
        offlineSessionMaxLifespanEnabledProperty.put("description", "");
        properties.put("offlineSessionMaxLifespanEnabled", offlineSessionMaxLifespanEnabledProperty);
        Map<String, Object> editUsernameAllowedProperty = new HashMap<>();
        editUsernameAllowedProperty.put("type", "string");
        editUsernameAllowedProperty.put("required", false);
        editUsernameAllowedProperty.put("description", "");
        properties.put("editUsernameAllowed", editUsernameAllowedProperty);
        Map<String, Object> internationalizationEnabledProperty = new HashMap<>();
        internationalizationEnabledProperty.put("type", "string");
        internationalizationEnabledProperty.put("required", false);
        internationalizationEnabledProperty.put("description", "");
        properties.put("internationalizationEnabled", internationalizationEnabledProperty);
        Map<String, Object> resetPasswordAllowedProperty = new HashMap<>();
        resetPasswordAllowedProperty.put("type", "string");
        resetPasswordAllowedProperty.put("required", false);
        resetPasswordAllowedProperty.put("description", "");
        properties.put("resetPasswordAllowed", resetPasswordAllowedProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> permanentLockoutProperty = new HashMap<>();
        permanentLockoutProperty.put("type", "string");
        permanentLockoutProperty.put("required", false);
        permanentLockoutProperty.put("description", "");
        properties.put("permanentLockout", permanentLockoutProperty);
        Map<String, Object> adminEventsDetailsEnabledProperty = new HashMap<>();
        adminEventsDetailsEnabledProperty.put("type", "string");
        adminEventsDetailsEnabledProperty.put("required", false);
        adminEventsDetailsEnabledProperty.put("description", "");
        properties.put("adminEventsDetailsEnabled", adminEventsDetailsEnabledProperty);
        Map<String, Object> duplicateEmailsAllowedProperty = new HashMap<>();
        duplicateEmailsAllowedProperty.put("type", "string");
        duplicateEmailsAllowedProperty.put("required", false);
        duplicateEmailsAllowedProperty.put("description", "");
        properties.put("duplicateEmailsAllowed", duplicateEmailsAllowedProperty);
        Map<String, Object> registrationAllowedProperty = new HashMap<>();
        registrationAllowedProperty.put("type", "string");
        registrationAllowedProperty.put("required", false);
        registrationAllowedProperty.put("description", "");
        properties.put("registrationAllowed", registrationAllowedProperty);
        Map<String, Object> registrationEmailAsUsernameProperty = new HashMap<>();
        registrationEmailAsUsernameProperty.put("type", "string");
        registrationEmailAsUsernameProperty.put("required", false);
        registrationEmailAsUsernameProperty.put("description", "");
        properties.put("registrationEmailAsUsername", registrationEmailAsUsernameProperty);
        Map<String, Object> revokeRefreshTokenProperty = new HashMap<>();
        revokeRefreshTokenProperty.put("type", "string");
        revokeRefreshTokenProperty.put("required", false);
        revokeRefreshTokenProperty.put("description", "");
        properties.put("revokeRefreshToken", revokeRefreshTokenProperty);
        Map<String, Object> adminEventsEnabledProperty = new HashMap<>();
        adminEventsEnabledProperty.put("type", "string");
        adminEventsEnabledProperty.put("required", false);
        adminEventsEnabledProperty.put("description", "");
        properties.put("adminEventsEnabled", adminEventsEnabledProperty);
        Map<String, Object> webAuthnPolicyAvoidSameAuthenticatorRegisterProperty = new HashMap<>();
        webAuthnPolicyAvoidSameAuthenticatorRegisterProperty.put("type", "string");
        webAuthnPolicyAvoidSameAuthenticatorRegisterProperty.put("required", false);
        webAuthnPolicyAvoidSameAuthenticatorRegisterProperty.put("description", "");
        properties.put("webAuthnPolicyAvoidSameAuthenticatorRegister", webAuthnPolicyAvoidSameAuthenticatorRegisterProperty);
        Map<String, Object> loginWithEmailAllowedProperty = new HashMap<>();
        loginWithEmailAllowedProperty.put("type", "string");
        loginWithEmailAllowedProperty.put("required", false);
        loginWithEmailAllowedProperty.put("description", "");
        properties.put("loginWithEmailAllowed", loginWithEmailAllowedProperty);
        Map<String, Object> bruteForceProtectedProperty = new HashMap<>();
        bruteForceProtectedProperty.put("type", "string");
        bruteForceProtectedProperty.put("required", false);
        bruteForceProtectedProperty.put("description", "");
        properties.put("bruteForceProtected", bruteForceProtectedProperty);
        Map<String, Object> eventsEnabledProperty = new HashMap<>();
        eventsEnabledProperty.put("type", "string");
        eventsEnabledProperty.put("required", false);
        eventsEnabledProperty.put("description", "");
        properties.put("eventsEnabled", eventsEnabledProperty);
        Map<String, Object> userManagedAccessAllowedProperty = new HashMap<>();
        userManagedAccessAllowedProperty.put("type", "string");
        userManagedAccessAllowedProperty.put("required", false);
        userManagedAccessAllowedProperty.put("description", "");
        properties.put("userManagedAccessAllowed", userManagedAccessAllowedProperty);
        Map<String, Object> rememberMeProperty = new HashMap<>();
        rememberMeProperty.put("type", "string");
        rememberMeProperty.put("required", false);
        rememberMeProperty.put("description", "");
        properties.put("rememberMe", rememberMeProperty);
        Map<String, Object> browserSecurityHeadersProperty = new HashMap<>();
        browserSecurityHeadersProperty.put("type", "string");
        browserSecurityHeadersProperty.put("required", false);
        browserSecurityHeadersProperty.put("description", "");
        properties.put("browserSecurityHeaders", browserSecurityHeadersProperty);
        Map<String, Object> clientScopeMappingsProperty = new HashMap<>();
        clientScopeMappingsProperty.put("type", "string");
        clientScopeMappingsProperty.put("required", false);
        clientScopeMappingsProperty.put("description", "");
        properties.put("clientScopeMappings", clientScopeMappingsProperty);
        Map<String, Object> rolesProperty = new HashMap<>();
        rolesProperty.put("type", "string");
        rolesProperty.put("required", false);
        rolesProperty.put("description", "");
        properties.put("roles", rolesProperty);
        Map<String, Object> componentsProperty = new HashMap<>();
        componentsProperty.put("type", "string");
        componentsProperty.put("required", false);
        componentsProperty.put("description", "");
        properties.put("components", componentsProperty);
        Map<String, Object> smtpServerProperty = new HashMap<>();
        smtpServerProperty.put("type", "string");
        smtpServerProperty.put("required", false);
        smtpServerProperty.put("description", "");
        properties.put("smtpServer", smtpServerProperty);
        Map<String, Object> attributesProperty = new HashMap<>();
        attributesProperty.put("type", "string");
        attributesProperty.put("required", false);
        attributesProperty.put("description", "");
        properties.put("attributes", attributesProperty);
        Map<String, Object> otpSupportedApplicationsProperty = new HashMap<>();
        otpSupportedApplicationsProperty.put("type", "string");
        otpSupportedApplicationsProperty.put("required", false);
        otpSupportedApplicationsProperty.put("description", "");
        properties.put("otpSupportedApplications", otpSupportedApplicationsProperty);
        Map<String, Object> requiredActionsProperty = new HashMap<>();
        requiredActionsProperty.put("type", "string");
        requiredActionsProperty.put("required", false);
        requiredActionsProperty.put("description", "");
        properties.put("requiredActions", requiredActionsProperty);
        Map<String, Object> supportedLocalesProperty = new HashMap<>();
        supportedLocalesProperty.put("type", "string");
        supportedLocalesProperty.put("required", false);
        supportedLocalesProperty.put("description", "");
        properties.put("supportedLocales", supportedLocalesProperty);
        Map<String, Object> clientScopesProperty = new HashMap<>();
        clientScopesProperty.put("type", "string");
        clientScopesProperty.put("required", false);
        clientScopesProperty.put("description", "");
        properties.put("clientScopes", clientScopesProperty);
        Map<String, Object> protocolMappersProperty = new HashMap<>();
        protocolMappersProperty.put("type", "string");
        protocolMappersProperty.put("required", false);
        protocolMappersProperty.put("description", "");
        properties.put("protocolMappers", protocolMappersProperty);
        Map<String, Object> webAuthnPolicyAcceptableAaguidsProperty = new HashMap<>();
        webAuthnPolicyAcceptableAaguidsProperty.put("type", "string");
        webAuthnPolicyAcceptableAaguidsProperty.put("required", false);
        webAuthnPolicyAcceptableAaguidsProperty.put("description", "");
        properties.put("webAuthnPolicyAcceptableAaguids", webAuthnPolicyAcceptableAaguidsProperty);
        Map<String, Object> authenticationFlowsProperty = new HashMap<>();
        authenticationFlowsProperty.put("type", "string");
        authenticationFlowsProperty.put("required", false);
        authenticationFlowsProperty.put("description", "");
        properties.put("authenticationFlows", authenticationFlowsProperty);
        Map<String, Object> identityProviderMappersProperty = new HashMap<>();
        identityProviderMappersProperty.put("type", "string");
        identityProviderMappersProperty.put("required", false);
        identityProviderMappersProperty.put("description", "");
        properties.put("identityProviderMappers", identityProviderMappersProperty);
        Map<String, Object> webAuthnPolicyPasswordlessAcceptableAaguidsProperty = new HashMap<>();
        webAuthnPolicyPasswordlessAcceptableAaguidsProperty.put("type", "string");
        webAuthnPolicyPasswordlessAcceptableAaguidsProperty.put("required", false);
        webAuthnPolicyPasswordlessAcceptableAaguidsProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessAcceptableAaguids", webAuthnPolicyPasswordlessAcceptableAaguidsProperty);
        Map<String, Object> webAuthnPolicySignatureAlgorithmsProperty = new HashMap<>();
        webAuthnPolicySignatureAlgorithmsProperty.put("type", "string");
        webAuthnPolicySignatureAlgorithmsProperty.put("required", false);
        webAuthnPolicySignatureAlgorithmsProperty.put("description", "");
        properties.put("webAuthnPolicySignatureAlgorithms", webAuthnPolicySignatureAlgorithmsProperty);
        Map<String, Object> identityProvidersProperty = new HashMap<>();
        identityProvidersProperty.put("type", "string");
        identityProvidersProperty.put("required", false);
        identityProvidersProperty.put("description", "");
        properties.put("identityProviders", identityProvidersProperty);
        Map<String, Object> eventsListenersProperty = new HashMap<>();
        eventsListenersProperty.put("type", "string");
        eventsListenersProperty.put("required", false);
        eventsListenersProperty.put("description", "");
        properties.put("eventsListeners", eventsListenersProperty);
        Map<String, Object> usersProperty = new HashMap<>();
        usersProperty.put("type", "string");
        usersProperty.put("required", false);
        usersProperty.put("description", "");
        properties.put("users", usersProperty);
        Map<String, Object> userFederationMappersProperty = new HashMap<>();
        userFederationMappersProperty.put("type", "string");
        userFederationMappersProperty.put("required", false);
        userFederationMappersProperty.put("description", "");
        properties.put("userFederationMappers", userFederationMappersProperty);
        Map<String, Object> scopeMappingsProperty = new HashMap<>();
        scopeMappingsProperty.put("type", "string");
        scopeMappingsProperty.put("required", false);
        scopeMappingsProperty.put("description", "");
        properties.put("scopeMappings", scopeMappingsProperty);
        Map<String, Object> enabledEventTypesProperty = new HashMap<>();
        enabledEventTypesProperty.put("type", "string");
        enabledEventTypesProperty.put("required", false);
        enabledEventTypesProperty.put("description", "");
        properties.put("enabledEventTypes", enabledEventTypesProperty);
        Map<String, Object> userFederationProvidersProperty = new HashMap<>();
        userFederationProvidersProperty.put("type", "string");
        userFederationProvidersProperty.put("required", false);
        userFederationProvidersProperty.put("description", "");
        properties.put("userFederationProviders", userFederationProvidersProperty);
        Map<String, Object> defaultRolesProperty = new HashMap<>();
        defaultRolesProperty.put("type", "string");
        defaultRolesProperty.put("required", false);
        defaultRolesProperty.put("description", "");
        properties.put("defaultRoles", defaultRolesProperty);
        Map<String, Object> federatedUsersProperty = new HashMap<>();
        federatedUsersProperty.put("type", "string");
        federatedUsersProperty.put("required", false);
        federatedUsersProperty.put("description", "");
        properties.put("federatedUsers", federatedUsersProperty);
        Map<String, Object> defaultOptionalClientScopesProperty = new HashMap<>();
        defaultOptionalClientScopesProperty.put("type", "string");
        defaultOptionalClientScopesProperty.put("required", false);
        defaultOptionalClientScopesProperty.put("description", "");
        properties.put("defaultOptionalClientScopes", defaultOptionalClientScopesProperty);
        Map<String, Object> groupsProperty = new HashMap<>();
        groupsProperty.put("type", "string");
        groupsProperty.put("required", false);
        groupsProperty.put("description", "");
        properties.put("groups", groupsProperty);
        Map<String, Object> defaultDefaultClientScopesProperty = new HashMap<>();
        defaultDefaultClientScopesProperty.put("type", "string");
        defaultDefaultClientScopesProperty.put("required", false);
        defaultDefaultClientScopesProperty.put("description", "");
        properties.put("defaultDefaultClientScopes", defaultDefaultClientScopesProperty);
        Map<String, Object> defaultGroupsProperty = new HashMap<>();
        defaultGroupsProperty.put("type", "string");
        defaultGroupsProperty.put("required", false);
        defaultGroupsProperty.put("description", "");
        properties.put("defaultGroups", defaultGroupsProperty);
        Map<String, Object> clientsProperty = new HashMap<>();
        clientsProperty.put("type", "string");
        clientsProperty.put("required", false);
        clientsProperty.put("description", "");
        properties.put("clients", clientsProperty);
        Map<String, Object> webAuthnPolicyPasswordlessSignatureAlgorithmsProperty = new HashMap<>();
        webAuthnPolicyPasswordlessSignatureAlgorithmsProperty.put("type", "string");
        webAuthnPolicyPasswordlessSignatureAlgorithmsProperty.put("required", false);
        webAuthnPolicyPasswordlessSignatureAlgorithmsProperty.put("description", "");
        properties.put("webAuthnPolicyPasswordlessSignatureAlgorithms", webAuthnPolicyPasswordlessSignatureAlgorithmsProperty);
        Map<String, Object> authenticatorConfigProperty = new HashMap<>();
        authenticatorConfigProperty.put("type", "string");
        authenticatorConfigProperty.put("required", false);
        authenticatorConfigProperty.put("description", "");
        properties.put("authenticatorConfig", authenticatorConfigProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm",
            "Update the top-level information of the realm   Any user, roles or client information in the representation  will be ignored.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_RealmHandler(config));
    }
    
}