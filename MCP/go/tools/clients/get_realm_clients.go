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

func Get_realm_clientsHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		queryParams := make([]string, 0)
		if val, ok := args["clientId"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("clientId=%v", val))
		}
		if val, ok := args["first"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("first=%v", val))
		}
		if val, ok := args["max"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("max=%v", val))
		}
		if val, ok := args["search"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("search=%v", val))
		}
		if val, ok := args["viewableOnly"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("viewableOnly=%v", val))
		}
		queryString := ""
		if len(queryParams) > 0 {
			queryString = "?" + strings.Join(queryParams, "&")
		}
		url := fmt.Sprintf("%s/%s/clients%s", cfg.BaseURL, queryString)
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

func CreateGet_realm_clientsTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("get_realm_clients",
		mcp.WithDescription("Get clients belonging to the realm   Returns a list of clients belonging to the realm"),
		mcp.WithString("clientId", mcp.Description("filter by clientId")),
		mcp.WithNumber("first", mcp.Description("the first result")),
		mcp.WithNumber("max", mcp.Description("the max results to return")),
		mcp.WithBoolean("search", mcp.Description("whether this is a search query or a getClientById query")),
		mcp.WithBoolean("viewableOnly", mcp.Description("filter clients that cannot be viewed in full by admin")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Get_realm_clientsHandler(cfg),
	}
}
