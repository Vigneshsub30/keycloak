/**
 * MCP Server function for Create a new user   Username must be unique.
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

class Post_Realm_UsersMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Realm_UsersHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("serviceAccountClientId")) {
            queryParams.add("serviceAccountClientId=" + args.get("serviceAccountClientId"));
        }
        if (args.containsKey("federationLink")) {
            queryParams.add("federationLink=" + args.get("federationLink"));
        }
        if (args.containsKey("origin")) {
            queryParams.add("origin=" + args.get("origin"));
        }
        if (args.containsKey("email")) {
            queryParams.add("email=" + args.get("email"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("lastName")) {
            queryParams.add("lastName=" + args.get("lastName"));
        }
        if (args.containsKey("self")) {
            queryParams.add("self=" + args.get("self"));
        }
        if (args.containsKey("username")) {
            queryParams.add("username=" + args.get("username"));
        }
        if (args.containsKey("firstName")) {
            queryParams.add("firstName=" + args.get("firstName"));
        }
        if (args.containsKey("notBefore")) {
            queryParams.add("notBefore=" + args.get("notBefore"));
        }
        if (args.containsKey("createdTimestamp")) {
            queryParams.add("createdTimestamp=" + args.get("createdTimestamp"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("emailVerified")) {
            queryParams.add("emailVerified=" + args.get("emailVerified"));
        }
        if (args.containsKey("clientRoles")) {
            queryParams.add("clientRoles=" + args.get("clientRoles"));
        }
        if (args.containsKey("attributes")) {
            queryParams.add("attributes=" + args.get("attributes"));
        }
        if (args.containsKey("access")) {
            queryParams.add("access=" + args.get("access"));
        }
        if (args.containsKey("clientConsents")) {
            queryParams.add("clientConsents=" + args.get("clientConsents"));
        }
        if (args.containsKey("federatedIdentities")) {
            queryParams.add("federatedIdentities=" + args.get("federatedIdentities"));
        }
        if (args.containsKey("disableableCredentialTypes")) {
            queryParams.add("disableableCredentialTypes=" + args.get("disableableCredentialTypes"));
        }
        if (args.containsKey("requiredActions")) {
            queryParams.add("requiredActions=" + args.get("requiredActions"));
        }
        if (args.containsKey("credentials")) {
            queryParams.add("credentials=" + args.get("credentials"));
        }
        if (args.containsKey("realmRoles")) {
            queryParams.add("realmRoles=" + args.get("realmRoles"));
        }
        if (args.containsKey("groups")) {
            queryParams.add("groups=" + args.get("groups"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_realm_users" + queryString;
                
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
    
    public static MCPServer.Tool createPost_Realm_UsersTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> serviceAccountClientIdProperty = new HashMap<>();
        serviceAccountClientIdProperty.put("type", "string");
        serviceAccountClientIdProperty.put("required", false);
        serviceAccountClientIdProperty.put("description", "");
        properties.put("serviceAccountClientId", serviceAccountClientIdProperty);
        Map<String, Object> federationLinkProperty = new HashMap<>();
        federationLinkProperty.put("type", "string");
        federationLinkProperty.put("required", false);
        federationLinkProperty.put("description", "");
        properties.put("federationLink", federationLinkProperty);
        Map<String, Object> originProperty = new HashMap<>();
        originProperty.put("type", "string");
        originProperty.put("required", false);
        originProperty.put("description", "");
        properties.put("origin", originProperty);
        Map<String, Object> emailProperty = new HashMap<>();
        emailProperty.put("type", "string");
        emailProperty.put("required", false);
        emailProperty.put("description", "");
        properties.put("email", emailProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> lastNameProperty = new HashMap<>();
        lastNameProperty.put("type", "string");
        lastNameProperty.put("required", false);
        lastNameProperty.put("description", "");
        properties.put("lastName", lastNameProperty);
        Map<String, Object> selfProperty = new HashMap<>();
        selfProperty.put("type", "string");
        selfProperty.put("required", false);
        selfProperty.put("description", "");
        properties.put("self", selfProperty);
        Map<String, Object> usernameProperty = new HashMap<>();
        usernameProperty.put("type", "string");
        usernameProperty.put("required", false);
        usernameProperty.put("description", "");
        properties.put("username", usernameProperty);
        Map<String, Object> firstNameProperty = new HashMap<>();
        firstNameProperty.put("type", "string");
        firstNameProperty.put("required", false);
        firstNameProperty.put("description", "");
        properties.put("firstName", firstNameProperty);
        Map<String, Object> notBeforeProperty = new HashMap<>();
        notBeforeProperty.put("type", "string");
        notBeforeProperty.put("required", false);
        notBeforeProperty.put("description", "");
        properties.put("notBefore", notBeforeProperty);
        Map<String, Object> createdTimestampProperty = new HashMap<>();
        createdTimestampProperty.put("type", "string");
        createdTimestampProperty.put("required", false);
        createdTimestampProperty.put("description", "");
        properties.put("createdTimestamp", createdTimestampProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> emailVerifiedProperty = new HashMap<>();
        emailVerifiedProperty.put("type", "string");
        emailVerifiedProperty.put("required", false);
        emailVerifiedProperty.put("description", "");
        properties.put("emailVerified", emailVerifiedProperty);
        Map<String, Object> clientRolesProperty = new HashMap<>();
        clientRolesProperty.put("type", "string");
        clientRolesProperty.put("required", false);
        clientRolesProperty.put("description", "");
        properties.put("clientRoles", clientRolesProperty);
        Map<String, Object> attributesProperty = new HashMap<>();
        attributesProperty.put("type", "string");
        attributesProperty.put("required", false);
        attributesProperty.put("description", "");
        properties.put("attributes", attributesProperty);
        Map<String, Object> accessProperty = new HashMap<>();
        accessProperty.put("type", "string");
        accessProperty.put("required", false);
        accessProperty.put("description", "");
        properties.put("access", accessProperty);
        Map<String, Object> clientConsentsProperty = new HashMap<>();
        clientConsentsProperty.put("type", "string");
        clientConsentsProperty.put("required", false);
        clientConsentsProperty.put("description", "");
        properties.put("clientConsents", clientConsentsProperty);
        Map<String, Object> federatedIdentitiesProperty = new HashMap<>();
        federatedIdentitiesProperty.put("type", "string");
        federatedIdentitiesProperty.put("required", false);
        federatedIdentitiesProperty.put("description", "");
        properties.put("federatedIdentities", federatedIdentitiesProperty);
        Map<String, Object> disableableCredentialTypesProperty = new HashMap<>();
        disableableCredentialTypesProperty.put("type", "string");
        disableableCredentialTypesProperty.put("required", false);
        disableableCredentialTypesProperty.put("description", "");
        properties.put("disableableCredentialTypes", disableableCredentialTypesProperty);
        Map<String, Object> requiredActionsProperty = new HashMap<>();
        requiredActionsProperty.put("type", "string");
        requiredActionsProperty.put("required", false);
        requiredActionsProperty.put("description", "");
        properties.put("requiredActions", requiredActionsProperty);
        Map<String, Object> credentialsProperty = new HashMap<>();
        credentialsProperty.put("type", "string");
        credentialsProperty.put("required", false);
        credentialsProperty.put("description", "");
        properties.put("credentials", credentialsProperty);
        Map<String, Object> realmRolesProperty = new HashMap<>();
        realmRolesProperty.put("type", "string");
        realmRolesProperty.put("required", false);
        realmRolesProperty.put("description", "");
        properties.put("realmRoles", realmRolesProperty);
        Map<String, Object> groupsProperty = new HashMap<>();
        groupsProperty.put("type", "string");
        groupsProperty.put("required", false);
        groupsProperty.put("description", "");
        properties.put("groups", groupsProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_realm_users",
            "Create a new user   Username must be unique.",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Realm_UsersHandler(config));
    }
    
}