/**
 * MCP Server function for Update the client
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

class Put_Realm_Clients_IdMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPut_Realm_Clients_IdHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("adminUrl")) {
            queryParams.add("adminUrl=" + args.get("adminUrl"));
        }
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
        if (args.containsKey("origin")) {
            queryParams.add("origin=" + args.get("origin"));
        }
        if (args.containsKey("name")) {
            queryParams.add("name=" + args.get("name"));
        }
        if (args.containsKey("protocol")) {
            queryParams.add("protocol=" + args.get("protocol"));
        }
        if (args.containsKey("baseUrl")) {
            queryParams.add("baseUrl=" + args.get("baseUrl"));
        }
        if (args.containsKey("rootUrl")) {
            queryParams.add("rootUrl=" + args.get("rootUrl"));
        }
        if (args.containsKey("secret")) {
            queryParams.add("secret=" + args.get("secret"));
        }
        if (args.containsKey("registrationAccessToken")) {
            queryParams.add("registrationAccessToken=" + args.get("registrationAccessToken"));
        }
        if (args.containsKey("clientAuthenticatorType")) {
            queryParams.add("clientAuthenticatorType=" + args.get("clientAuthenticatorType"));
        }
        if (args.containsKey("description")) {
            queryParams.add("description=" + args.get("description"));
        }
        if (args.containsKey("clientId")) {
            queryParams.add("clientId=" + args.get("clientId"));
        }
        if (args.containsKey("nodeReRegistrationTimeout")) {
            queryParams.add("nodeReRegistrationTimeout=" + args.get("nodeReRegistrationTimeout"));
        }
        if (args.containsKey("notBefore")) {
            queryParams.add("notBefore=" + args.get("notBefore"));
        }
        if (args.containsKey("serviceAccountsEnabled")) {
            queryParams.add("serviceAccountsEnabled=" + args.get("serviceAccountsEnabled"));
        }
        if (args.containsKey("fullScopeAllowed")) {
            queryParams.add("fullScopeAllowed=" + args.get("fullScopeAllowed"));
        }
        if (args.containsKey("directAccessGrantsEnabled")) {
            queryParams.add("directAccessGrantsEnabled=" + args.get("directAccessGrantsEnabled"));
        }
        if (args.containsKey("enabled")) {
            queryParams.add("enabled=" + args.get("enabled"));
        }
        if (args.containsKey("bearerOnly")) {
            queryParams.add("bearerOnly=" + args.get("bearerOnly"));
        }
        if (args.containsKey("authorizationServicesEnabled")) {
            queryParams.add("authorizationServicesEnabled=" + args.get("authorizationServicesEnabled"));
        }
        if (args.containsKey("frontchannelLogout")) {
            queryParams.add("frontchannelLogout=" + args.get("frontchannelLogout"));
        }
        if (args.containsKey("surrogateAuthRequired")) {
            queryParams.add("surrogateAuthRequired=" + args.get("surrogateAuthRequired"));
        }
        if (args.containsKey("alwaysDisplayInConsole")) {
            queryParams.add("alwaysDisplayInConsole=" + args.get("alwaysDisplayInConsole"));
        }
        if (args.containsKey("publicClient")) {
            queryParams.add("publicClient=" + args.get("publicClient"));
        }
        if (args.containsKey("standardFlowEnabled")) {
            queryParams.add("standardFlowEnabled=" + args.get("standardFlowEnabled"));
        }
        if (args.containsKey("implicitFlowEnabled")) {
            queryParams.add("implicitFlowEnabled=" + args.get("implicitFlowEnabled"));
        }
        if (args.containsKey("consentRequired")) {
            queryParams.add("consentRequired=" + args.get("consentRequired"));
        }
        if (args.containsKey("authenticationFlowBindingOverrides")) {
            queryParams.add("authenticationFlowBindingOverrides=" + args.get("authenticationFlowBindingOverrides"));
        }
        if (args.containsKey("registeredNodes")) {
            queryParams.add("registeredNodes=" + args.get("registeredNodes"));
        }
        if (args.containsKey("attributes")) {
            queryParams.add("attributes=" + args.get("attributes"));
        }
        if (args.containsKey("access")) {
            queryParams.add("access=" + args.get("access"));
        }
        if (args.containsKey("authorizationSettings")) {
            queryParams.add("authorizationSettings=" + args.get("authorizationSettings"));
        }
        if (args.containsKey("protocolMappers")) {
            queryParams.add("protocolMappers=" + args.get("protocolMappers"));
        }
        if (args.containsKey("optionalClientScopes")) {
            queryParams.add("optionalClientScopes=" + args.get("optionalClientScopes"));
        }
        if (args.containsKey("webOrigins")) {
            queryParams.add("webOrigins=" + args.get("webOrigins"));
        }
        if (args.containsKey("redirectUris")) {
            queryParams.add("redirectUris=" + args.get("redirectUris"));
        }
        if (args.containsKey("defaultRoles")) {
            queryParams.add("defaultRoles=" + args.get("defaultRoles"));
        }
        if (args.containsKey("defaultClientScopes")) {
            queryParams.add("defaultClientScopes=" + args.get("defaultClientScopes"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/put_realm_clients_id" + queryString;
                
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
    
    public static MCPServer.Tool createPut_Realm_Clients_IdTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> adminUrlProperty = new HashMap<>();
        adminUrlProperty.put("type", "string");
        adminUrlProperty.put("required", false);
        adminUrlProperty.put("description", "");
        properties.put("adminUrl", adminUrlProperty);
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "");
        properties.put("id", idProperty);
        Map<String, Object> originProperty = new HashMap<>();
        originProperty.put("type", "string");
        originProperty.put("required", false);
        originProperty.put("description", "");
        properties.put("origin", originProperty);
        Map<String, Object> nameProperty = new HashMap<>();
        nameProperty.put("type", "string");
        nameProperty.put("required", false);
        nameProperty.put("description", "");
        properties.put("name", nameProperty);
        Map<String, Object> protocolProperty = new HashMap<>();
        protocolProperty.put("type", "string");
        protocolProperty.put("required", false);
        protocolProperty.put("description", "");
        properties.put("protocol", protocolProperty);
        Map<String, Object> baseUrlProperty = new HashMap<>();
        baseUrlProperty.put("type", "string");
        baseUrlProperty.put("required", false);
        baseUrlProperty.put("description", "");
        properties.put("baseUrl", baseUrlProperty);
        Map<String, Object> rootUrlProperty = new HashMap<>();
        rootUrlProperty.put("type", "string");
        rootUrlProperty.put("required", false);
        rootUrlProperty.put("description", "");
        properties.put("rootUrl", rootUrlProperty);
        Map<String, Object> secretProperty = new HashMap<>();
        secretProperty.put("type", "string");
        secretProperty.put("required", false);
        secretProperty.put("description", "");
        properties.put("secret", secretProperty);
        Map<String, Object> registrationAccessTokenProperty = new HashMap<>();
        registrationAccessTokenProperty.put("type", "string");
        registrationAccessTokenProperty.put("required", false);
        registrationAccessTokenProperty.put("description", "");
        properties.put("registrationAccessToken", registrationAccessTokenProperty);
        Map<String, Object> clientAuthenticatorTypeProperty = new HashMap<>();
        clientAuthenticatorTypeProperty.put("type", "string");
        clientAuthenticatorTypeProperty.put("required", false);
        clientAuthenticatorTypeProperty.put("description", "");
        properties.put("clientAuthenticatorType", clientAuthenticatorTypeProperty);
        Map<String, Object> descriptionProperty = new HashMap<>();
        descriptionProperty.put("type", "string");
        descriptionProperty.put("required", false);
        descriptionProperty.put("description", "");
        properties.put("description", descriptionProperty);
        Map<String, Object> clientIdProperty = new HashMap<>();
        clientIdProperty.put("type", "string");
        clientIdProperty.put("required", false);
        clientIdProperty.put("description", "");
        properties.put("clientId", clientIdProperty);
        Map<String, Object> nodeReRegistrationTimeoutProperty = new HashMap<>();
        nodeReRegistrationTimeoutProperty.put("type", "string");
        nodeReRegistrationTimeoutProperty.put("required", false);
        nodeReRegistrationTimeoutProperty.put("description", "");
        properties.put("nodeReRegistrationTimeout", nodeReRegistrationTimeoutProperty);
        Map<String, Object> notBeforeProperty = new HashMap<>();
        notBeforeProperty.put("type", "string");
        notBeforeProperty.put("required", false);
        notBeforeProperty.put("description", "");
        properties.put("notBefore", notBeforeProperty);
        Map<String, Object> serviceAccountsEnabledProperty = new HashMap<>();
        serviceAccountsEnabledProperty.put("type", "string");
        serviceAccountsEnabledProperty.put("required", false);
        serviceAccountsEnabledProperty.put("description", "");
        properties.put("serviceAccountsEnabled", serviceAccountsEnabledProperty);
        Map<String, Object> fullScopeAllowedProperty = new HashMap<>();
        fullScopeAllowedProperty.put("type", "string");
        fullScopeAllowedProperty.put("required", false);
        fullScopeAllowedProperty.put("description", "");
        properties.put("fullScopeAllowed", fullScopeAllowedProperty);
        Map<String, Object> directAccessGrantsEnabledProperty = new HashMap<>();
        directAccessGrantsEnabledProperty.put("type", "string");
        directAccessGrantsEnabledProperty.put("required", false);
        directAccessGrantsEnabledProperty.put("description", "");
        properties.put("directAccessGrantsEnabled", directAccessGrantsEnabledProperty);
        Map<String, Object> enabledProperty = new HashMap<>();
        enabledProperty.put("type", "string");
        enabledProperty.put("required", false);
        enabledProperty.put("description", "");
        properties.put("enabled", enabledProperty);
        Map<String, Object> bearerOnlyProperty = new HashMap<>();
        bearerOnlyProperty.put("type", "string");
        bearerOnlyProperty.put("required", false);
        bearerOnlyProperty.put("description", "");
        properties.put("bearerOnly", bearerOnlyProperty);
        Map<String, Object> authorizationServicesEnabledProperty = new HashMap<>();
        authorizationServicesEnabledProperty.put("type", "string");
        authorizationServicesEnabledProperty.put("required", false);
        authorizationServicesEnabledProperty.put("description", "");
        properties.put("authorizationServicesEnabled", authorizationServicesEnabledProperty);
        Map<String, Object> frontchannelLogoutProperty = new HashMap<>();
        frontchannelLogoutProperty.put("type", "string");
        frontchannelLogoutProperty.put("required", false);
        frontchannelLogoutProperty.put("description", "");
        properties.put("frontchannelLogout", frontchannelLogoutProperty);
        Map<String, Object> surrogateAuthRequiredProperty = new HashMap<>();
        surrogateAuthRequiredProperty.put("type", "string");
        surrogateAuthRequiredProperty.put("required", false);
        surrogateAuthRequiredProperty.put("description", "");
        properties.put("surrogateAuthRequired", surrogateAuthRequiredProperty);
        Map<String, Object> alwaysDisplayInConsoleProperty = new HashMap<>();
        alwaysDisplayInConsoleProperty.put("type", "string");
        alwaysDisplayInConsoleProperty.put("required", false);
        alwaysDisplayInConsoleProperty.put("description", "");
        properties.put("alwaysDisplayInConsole", alwaysDisplayInConsoleProperty);
        Map<String, Object> publicClientProperty = new HashMap<>();
        publicClientProperty.put("type", "string");
        publicClientProperty.put("required", false);
        publicClientProperty.put("description", "");
        properties.put("publicClient", publicClientProperty);
        Map<String, Object> standardFlowEnabledProperty = new HashMap<>();
        standardFlowEnabledProperty.put("type", "string");
        standardFlowEnabledProperty.put("required", false);
        standardFlowEnabledProperty.put("description", "");
        properties.put("standardFlowEnabled", standardFlowEnabledProperty);
        Map<String, Object> implicitFlowEnabledProperty = new HashMap<>();
        implicitFlowEnabledProperty.put("type", "string");
        implicitFlowEnabledProperty.put("required", false);
        implicitFlowEnabledProperty.put("description", "");
        properties.put("implicitFlowEnabled", implicitFlowEnabledProperty);
        Map<String, Object> consentRequiredProperty = new HashMap<>();
        consentRequiredProperty.put("type", "string");
        consentRequiredProperty.put("required", false);
        consentRequiredProperty.put("description", "");
        properties.put("consentRequired", consentRequiredProperty);
        Map<String, Object> authenticationFlowBindingOverridesProperty = new HashMap<>();
        authenticationFlowBindingOverridesProperty.put("type", "string");
        authenticationFlowBindingOverridesProperty.put("required", false);
        authenticationFlowBindingOverridesProperty.put("description", "");
        properties.put("authenticationFlowBindingOverrides", authenticationFlowBindingOverridesProperty);
        Map<String, Object> registeredNodesProperty = new HashMap<>();
        registeredNodesProperty.put("type", "string");
        registeredNodesProperty.put("required", false);
        registeredNodesProperty.put("description", "");
        properties.put("registeredNodes", registeredNodesProperty);
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
        Map<String, Object> authorizationSettingsProperty = new HashMap<>();
        authorizationSettingsProperty.put("type", "string");
        authorizationSettingsProperty.put("required", false);
        authorizationSettingsProperty.put("description", "");
        properties.put("authorizationSettings", authorizationSettingsProperty);
        Map<String, Object> protocolMappersProperty = new HashMap<>();
        protocolMappersProperty.put("type", "string");
        protocolMappersProperty.put("required", false);
        protocolMappersProperty.put("description", "");
        properties.put("protocolMappers", protocolMappersProperty);
        Map<String, Object> optionalClientScopesProperty = new HashMap<>();
        optionalClientScopesProperty.put("type", "string");
        optionalClientScopesProperty.put("required", false);
        optionalClientScopesProperty.put("description", "");
        properties.put("optionalClientScopes", optionalClientScopesProperty);
        Map<String, Object> webOriginsProperty = new HashMap<>();
        webOriginsProperty.put("type", "string");
        webOriginsProperty.put("required", false);
        webOriginsProperty.put("description", "");
        properties.put("webOrigins", webOriginsProperty);
        Map<String, Object> redirectUrisProperty = new HashMap<>();
        redirectUrisProperty.put("type", "string");
        redirectUrisProperty.put("required", false);
        redirectUrisProperty.put("description", "");
        properties.put("redirectUris", redirectUrisProperty);
        Map<String, Object> defaultRolesProperty = new HashMap<>();
        defaultRolesProperty.put("type", "string");
        defaultRolesProperty.put("required", false);
        defaultRolesProperty.put("description", "");
        properties.put("defaultRoles", defaultRolesProperty);
        Map<String, Object> defaultClientScopesProperty = new HashMap<>();
        defaultClientScopesProperty.put("type", "string");
        defaultClientScopesProperty.put("required", false);
        defaultClientScopesProperty.put("description", "");
        properties.put("defaultClientScopes", defaultClientScopesProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "put_realm_clients_id",
            "Update the client",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPut_Realm_Clients_IdHandler(config));
    }
    
}