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

func Get_realm_eventsHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		queryParams := make([]string, 0)
		if val, ok := args["client"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("client=%v", val))
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
		if val, ok := args["ipAddress"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("ipAddress=%v", val))
		}
		if val, ok := args["max"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("max=%v", val))
		}
		if val, ok := args["type"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("type=%v", val))
		}
		if val, ok := args["user"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("user=%v", val))
		}
		queryString := ""
		if len(queryParams) > 0 {
			queryString = "?" + strings.Join(queryParams, "&")
		}
		url := fmt.Sprintf("%s/%s/events%s", cfg.BaseURL, queryString)
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

func CreateGet_realm_eventsTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("get_realm_events",
		mcp.WithDescription("Get events   Returns all events, or filters them based on URL query parameters listed here"),
		mcp.WithString("client", mcp.Description("App or oauth client name")),
		mcp.WithString("dateFrom", mcp.Description("From date")),
		mcp.WithString("dateTo", mcp.Description("To date")),
		mcp.WithNumber("first", mcp.Description("Paging offset")),
		mcp.WithString("ipAddress", mcp.Description("IP address")),
		mcp.WithNumber("max", mcp.Description("Maximum results size (defaults to 100)")),
		mcp.WithArray("type", mcp.Description("The types of events to return")),
		mcp.WithString("user", mcp.Description("User id")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Get_realm_eventsHandler(cfg),
	}
}
