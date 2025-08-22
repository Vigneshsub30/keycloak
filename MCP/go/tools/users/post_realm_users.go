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

func Post_realm_usersHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		// Create properly typed request body using the generated schema
		var requestBody models.UserRepresentation
		
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
		url := fmt.Sprintf("%s/%s/users", cfg.BaseURL)
		req, err := http.NewRequest("POST", url, bytes.NewBuffer(bodyBytes))
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

func CreatePost_realm_usersTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("post_realm_users",
		mcp.WithDescription("Create a new user   Username must be unique."),
		mcp.WithString("serviceAccountClientId", mcp.Description("")),
		mcp.WithBoolean("enabled", mcp.Description("")),
		mcp.WithObject("clientRoles", mcp.Description("")),
		mcp.WithNumber("notBefore", mcp.Description("")),
		mcp.WithArray("clientConsents", mcp.Description("")),
		mcp.WithNumber("createdTimestamp", mcp.Description("")),
		mcp.WithArray("federatedIdentities", mcp.Description("")),
		mcp.WithString("federationLink", mcp.Description("")),
		mcp.WithBoolean("emailVerified", mcp.Description("")),
		mcp.WithArray("disableableCredentialTypes", mcp.Description("")),
		mcp.WithString("origin", mcp.Description("")),
		mcp.WithString("email", mcp.Description("")),
		mcp.WithString("id", mcp.Description("")),
		mcp.WithObject("attributes", mcp.Description("")),
		mcp.WithString("lastName", mcp.Description("")),
		mcp.WithString("self", mcp.Description("")),
		mcp.WithObject("access", mcp.Description("")),
		mcp.WithString("username", mcp.Description("")),
		mcp.WithArray("requiredActions", mcp.Description("")),
		mcp.WithArray("credentials", mcp.Description("")),
		mcp.WithArray("realmRoles", mcp.Description("")),
		mcp.WithString("firstName", mcp.Description("")),
		mcp.WithArray("groups", mcp.Description("")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Post_realm_usersHandler(cfg),
	}
}
