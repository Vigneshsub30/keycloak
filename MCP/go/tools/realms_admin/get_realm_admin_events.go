package tools

import (
	"context"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"

	"github.com/keycloak-admin-rest-api/mcp-server/config"
	"github.com/keycloak-admin-rest-api/mcp-server/models"
	"github.com/mark3labs/mcp-go/mcp"
)

func Get_realm_admin_eventsHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		queryParams := make([]string, 0)
		if val, ok := args["authClient"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("authClient=%v", val))
		}
		if val, ok := args["authIpAddress"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("authIpAddress=%v", val))
		}
		if val, ok := args["authRealm"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("authRealm=%v", val))
		}
		if val, ok := args["authUser"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("authUser=%v", val))
		}
		if val, ok := args["dateFrom"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("dateFrom=%v", val))
		}
		if val, ok := args["dateTo"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("dateTo=%v", val))
		}
		if val, ok := args["first"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("first=%v", val))
		}
		if val, ok := args["max"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("max=%v", val))
		}
		if val, ok := args["operationTypes"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("operationTypes=%v", val))
		}
		if val, ok := args["resourcePath"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("resourcePath=%v", val))
		}
		if val, ok := args["resourceTypes"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("resourceTypes=%v", val))
		}
		queryString := ""
		if len(queryParams) > 0 {
			queryString = "?" + strings.Join(queryParams, "&")
		}
		url := fmt.Sprintf("%s/%s/admin-events%s", cfg.BaseURL, queryString)
		req, err := http.NewRequest("GET", url, nil)
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

func CreateGet_realm_admin_eventsTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("get_realm_admin-events",
		mcp.WithDescription("Get admin events   Returns all admin events, or filters events based on URL query parameters listed here"),
		mcp.WithString("authClient", mcp.Description("")),
		mcp.WithString("authIpAddress", mcp.Description("")),
		mcp.WithString("authRealm", mcp.Description("")),
		mcp.WithString("authUser", mcp.Description("user id")),
		mcp.WithString("dateFrom", mcp.Description("")),
		mcp.WithString("dateTo", mcp.Description("")),
		mcp.WithNumber("first", mcp.Description("")),
		mcp.WithNumber("max", mcp.Description("Maximum results size (defaults to 100)")),
		mcp.WithArray("operationTypes", mcp.Description("")),
		mcp.WithString("resourcePath", mcp.Description("")),
		mcp.WithArray("resourceTypes", mcp.Description("")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Get_realm_admin_eventsHandler(cfg),
	}
}
