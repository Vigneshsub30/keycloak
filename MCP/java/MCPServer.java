/**
 * Main MCP Server - Handles MCP JSON-RPC protocol
 */

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

// Import Get_Realm_Client_Registration_Policy_ProvidersMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_UsersMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_GroupsMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_Composites_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Get_Realm_RolesMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_NameMCPTool - included in same package
// Import Post_Realm_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Get_Realm_Roles_Role_NameMCPTool - included in same package
// Import Put_Realm_Roles_Role_Name_Management_PermissionsMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_Composites_Clients_ClientMCPTool - included in same package
// Import Put_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsMCPTool - included in same package
// Import Post_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_GroupsMCPTool - included in same package
// Import Put_Realm_Clients_Id_Roles_Role_NameMCPTool - included in same package
// Import Put_Realm_Roles_Role_NameMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_Composites_RealmMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Roles_Role_NameMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsMCPTool - included in same package
// Import Delete_Realm_Roles_Role_NameMCPTool - included in same package
// Import Get_Realm_Clients_Id_RolesMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_Management_PermissionsMCPTool - included in same package
// Import Post_Realm_Clients_Id_RolesMCPTool - included in same package
// Import Get_Realm_Clients_Id_Roles_Role_Name_UsersMCPTool - included in same package
// Import Get_Realm_Roles_Role_Name_Composites_RealmMCPTool - included in same package
// Import Delete_Realm_Roles_Role_Name_CompositesMCPTool - included in same package
// Import Post_Realm_RolesMCPTool - included in same package
// Import Post_Realm_User_Storage_Parent_Id_Mappers_Id_SyncMCPTool - included in same package
// Import Post_Realm_User_Storage_Id_Remove_Imported_UsersMCPTool - included in same package
// Import Post_Realm_User_Storage_Id_SyncMCPTool - included in same package
// Import Get_Id_NameMCPTool - included in same package
// Import Post_Realm_User_Storage_Id_Unlink_UsersMCPTool - included in same package
// Import Get_Realm_User_Storage_Id_NameMCPTool - included in same package
// Import Put_Realm_Roles_By_Id_Role_Id_Management_PermissionsMCPTool - included in same package
// Import Get_Realm_Roles_By_Id_Role_IdMCPTool - included in same package
// Import Delete_Realm_Roles_By_Id_Role_Id_CompositesMCPTool - included in same package
// Import Get_Realm_Roles_By_Id_Role_Id_Composites_Clients_ClientMCPTool - included in same package
// Import Put_Realm_Roles_By_Id_Role_IdMCPTool - included in same package
// Import Get_Realm_Roles_By_Id_Role_Id_Management_PermissionsMCPTool - included in same package
// Import Delete_Realm_Roles_By_Id_Role_IdMCPTool - included in same package
// Import Get_Realm_Roles_By_Id_Role_Id_Composites_RealmMCPTool - included in same package
// Import Post_Realm_Roles_By_Id_Role_Id_CompositesMCPTool - included in same package
// Import Get_Realm_Roles_By_Id_Role_Id_CompositesMCPTool - included in same package
// Import Get_Realm_KeysMCPTool - included in same package
// Import GetMCPTool - included in same package
// Import Delete_Realm_Attack_Detection_Brute_Force_Users_User_IdMCPTool - included in same package
// Import Delete_Realm_Attack_Detection_Brute_Force_UsersMCPTool - included in same package
// Import Get_Realm_Attack_Detection_Brute_Force_Users_User_IdMCPTool - included in same package
// Import Get_Realm_Users_Id_Groups_CountMCPTool - included in same package
// Import Delete_Realm_Users_IdMCPTool - included in same package
// Import Delete_Realm_Users_Id_Groups_Group_IdMCPTool - included in same package
// Import Get_Realm_UsersMCPTool - included in same package
// Import Put_Realm_Users_Id_Groups_Group_IdMCPTool - included in same package
// Import Put_Realm_Users_Id_Disable_Credential_TypesMCPTool - included in same package
// Import Get_Realm_Users_Id_Configured_User_Storage_Credential_TypesMCPTool - included in same package
// Import Delete_Realm_Users_Id_Consents_ClientMCPTool - included in same package
// Import Put_Realm_Users_Id_Credentials_Credential_Id_User_LabelMCPTool - included in same package
// Import Put_Realm_Users_Id_Send_Verify_EmailMCPTool - included in same package
// Import Get_Realm_Users_IdMCPTool - included in same package
// Import Get_Realm_Users_CountMCPTool - included in same package
// Import Get_Realm_Users_Id_ConsentsMCPTool - included in same package
// Import Post_Realm_UsersMCPTool - included in same package
// Import Get_Realm_Users_Id_Federated_IdentityMCPTool - included in same package
// Import Post_Realm_Users_Id_LogoutMCPTool - included in same package
// Import Delete_Realm_Users_Id_Credentials_Credential_IdMCPTool - included in same package
// Import Get_Realm_Users_Id_SessionsMCPTool - included in same package
// Import Get_Realm_Users_Id_Offline_Sessions_Client_IdMCPTool - included in same package
// Import Post_Realm_Users_Id_Federated_Identity_ProviderMCPTool - included in same package
// Import Get_Realm_Users_Id_GroupsMCPTool - included in same package
// Import Delete_Realm_Users_Id_Federated_Identity_ProviderMCPTool - included in same package
// Import Get_Realm_Users_Id_CredentialsMCPTool - included in same package
// Import Put_Realm_Users_IdMCPTool - included in same package
// Import Put_Realm_Users_Id_Reset_PasswordMCPTool - included in same package
// Import Post_Realm_Users_Id_ImpersonationMCPTool - included in same package
// Import Post_Realm_Users_Id_Credentials_Credential_Id_Move_To_FirstMCPTool - included in same package
// Import Put_Realm_Users_Id_Execute_Actions_EmailMCPTool - included in same package
// Import Post_Realm_Users_Id_Credentials_Credential_Id_Move_After_New_Previous_Credential_IdMCPTool - included in same package
// Import Post_Realm_Users_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Delete_Realm_Groups_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_MappingsMCPTool - included in same package
// Import Post_Realm_Groups_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_Realm_CompositeMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_Realm_AvailableMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_Realm_CompositeMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_MappingsMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_Realm_AvailableMCPTool - included in same package
// Import Delete_Realm_Users_Id_Role_Mappings_RealmMCPTool - included in same package
// Import Put_Realm_Groups_IdMCPTool - included in same package
// Import Put_Realm_Groups_Id_Management_PermissionsMCPTool - included in same package
// Import Get_Realm_Groups_Id_MembersMCPTool - included in same package
// Import Delete_Realm_Groups_IdMCPTool - included in same package
// Import Post_Realm_GroupsMCPTool - included in same package
// Import Get_Realm_Groups_IdMCPTool - included in same package
// Import Get_Realm_Groups_CountMCPTool - included in same package
// Import Get_Realm_Groups_Id_Management_PermissionsMCPTool - included in same package
// Import Post_Realm_Groups_Id_ChildrenMCPTool - included in same package
// Import Get_Realm_GroupsMCPTool - included in same package
// Import Post_Realm_Authentication_Executions_Execution_Id_Raise_PriorityMCPTool - included in same package
// Import Get_Realm_Authentication_Unregistered_Required_ActionsMCPTool - included in same package
// Import Get_Realm_Authentication_Per_Client_Config_DescriptionMCPTool - included in same package
// Import Post_Realm_Authentication_FlowsMCPTool - included in same package
// Import Delete_Realm_Authentication_Flows_IdMCPTool - included in same package
// Import Put_Realm_Authentication_Flows_Flow_Alias_ExecutionsMCPTool - included in same package
// Import Get_Realm_Authentication_Form_Action_ProvidersMCPTool - included in same package
// Import Put_Realm_Authentication_Flows_IdMCPTool - included in same package
// Import Get_Realm_Authentication_Config_Description_Provider_IdMCPTool - included in same package
// Import Get_Realm_Authentication_Client_Authenticator_ProvidersMCPTool - included in same package
// Import Get_Realm_Authentication_FlowsMCPTool - included in same package
// Import Delete_Realm_Authentication_Config_IdMCPTool - included in same package
// Import Get_Realm_Authentication_Required_Actions_AliasMCPTool - included in same package
// Import Get_Realm_Authentication_Config_IdMCPTool - included in same package
// Import Get_Realm_Authentication_Required_ActionsMCPTool - included in same package
// Import Get_Realm_Authentication_Flows_Flow_Alias_ExecutionsMCPTool - included in same package
// Import Post_Realm_Authentication_Required_Actions_Alias_Raise_PriorityMCPTool - included in same package
// Import Post_Realm_Authentication_Flows_Flow_Alias_Executions_ExecutionMCPTool - included in same package
// Import Post_Realm_Authentication_ExecutionsMCPTool - included in same package
// Import Post_Realm_Authentication_Executions_Execution_Id_ConfigMCPTool - included in same package
// Import Get_Realm_Authentication_Flows_IdMCPTool - included in same package
// Import Get_Realm_Authentication_Form_ProvidersMCPTool - included in same package
// Import Get_Realm_Authentication_Authenticator_ProvidersMCPTool - included in same package
// Import Get_Realm_Authentication_Executions_Execution_IdMCPTool - included in same package
// Import Post_Realm_Authentication_Flows_Flow_Alias_Executions_FlowMCPTool - included in same package
// Import Post_Realm_Authentication_Flows_Flow_Alias_CopyMCPTool - included in same package
// Import Post_Realm_Authentication_Required_Actions_Alias_Lower_PriorityMCPTool - included in same package
// Import Put_Realm_Authentication_Config_IdMCPTool - included in same package
// Import Delete_Realm_Authentication_Required_Actions_AliasMCPTool - included in same package
// Import Post_Realm_Authentication_Executions_Execution_Id_Lower_PriorityMCPTool - included in same package
// Import Delete_Realm_Authentication_Executions_Execution_IdMCPTool - included in same package
// Import Post_Realm_Authentication_Register_Required_ActionMCPTool - included in same package
// Import Put_Realm_Authentication_Required_Actions_AliasMCPTool - included in same package
// Import Post_Realm_ComponentsMCPTool - included in same package
// Import Get_Realm_Components_Id_Sub_Component_TypesMCPTool - included in same package
// Import Get_Realm_ComponentsMCPTool - included in same package
// Import Get_Realm_Components_IdMCPTool - included in same package
// Import Put_Realm_Components_IdMCPTool - included in same package
// Import Delete_Realm_Components_IdMCPTool - included in same package
// Import Put_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Delete_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Put_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Get_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Get_Realm_Clients_Id_Protocol_Mappers_ModelsMCPTool - included in same package
// Import Post_Realm_Clients_Id_Protocol_Mappers_Add_ModelsMCPTool - included in same package
// Import Get_Realm_Clients_Id_Protocol_Mappers_Protocol_ProtocolMCPTool - included in same package
// Import Post_Realm_Client_Scopes_Id_Protocol_Mappers_Add_ModelsMCPTool - included in same package
// Import Delete_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool - included in same package
// Import Post_Realm_Clients_Id_Protocol_Mappers_ModelsMCPTool - included in same package
// Import Post_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Protocol_Mappers_Protocol_ProtocolMCPTool - included in same package
// Import Delete_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool - included in same package
// Import Put_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Providers_Provider_IdMCPTool - included in same package
// Import Post_Realm_Identity_Provider_InstancesMCPTool - included in same package
// Import Get_Realm_Identity_Provider_InstancesMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_AliasMCPTool - included in same package
// Import Delete_Realm_Identity_Provider_Instances_AliasMCPTool - included in same package
// Import Put_Realm_Identity_Provider_Instances_AliasMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_Alias_ExportMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_Alias_MappersMCPTool - included in same package
// Import Put_Realm_Identity_Provider_Instances_Alias_Management_PermissionsMCPTool - included in same package
// Import Post_Realm_Identity_Provider_Import_ConfigMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_Alias_Management_PermissionsMCPTool - included in same package
// Import Get_Realm_Identity_Provider_Instances_Alias_Mapper_TypesMCPTool - included in same package
// Import Post_Realm_Identity_Provider_Instances_Alias_MappersMCPTool - included in same package
// Import Get_Realm_Client_ScopesMCPTool - included in same package
// Import Put_Realm_Client_Scopes_IdMCPTool - included in same package
// Import Get_Realm_Client_Scopes_IdMCPTool - included in same package
// Import Delete_Realm_Client_Scopes_IdMCPTool - included in same package
// Import Post_Realm_Client_ScopesMCPTool - included in same package
// Import Get_Realm_Clients_Id_Certificates_AttrMCPTool - included in same package
// Import Post_Realm_Clients_Id_Certificates_Attr_GenerateMCPTool - included in same package
// Import Post_Realm_Clients_Id_Certificates_Attr_Upload_CertificateMCPTool - included in same package
// Import Post_Realm_Clients_Id_Certificates_Attr_UploadMCPTool - included in same package
// Import Post_Realm_Clients_Initial_AccessMCPTool - included in same package
// Import Delete_Realm_Clients_Initial_Access_IdMCPTool - included in same package
// Import Get_Realm_Clients_Initial_AccessMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_AvailableMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Post_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_Realm_CompositeMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_Clients_Client_CompositeMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_MappingsMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_Realm_AvailableMCPTool - included in same package
// Import Delete_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_Realm_AvailableMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_CompositeMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Post_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Post_Realm_Clients_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Post_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Scope_Mappings_RealmMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_Realm_CompositeMCPTool - included in same package
// Import Get_Realm_Clients_Id_Scope_Mappings_Clients_Client_AvailableMCPTool - included in same package
// Import Get_Realm_Client_Scopes_Id_Scope_MappingsMCPTool - included in same package
// Import Post_Realm_Logout_AllMCPTool - included in same package
// Import Get_Realm_Default_Default_Client_ScopesMCPTool - included in same package
// Import Post_Realm_Partial_ExportMCPTool - included in same package
// Import Post_Realm_Client_Description_ConverterMCPTool - included in same package
// Import Put_Realm_Default_Optional_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_EventsMCPTool - included in same package
// Import Delete_RealmMCPTool - included in same package
// Import Get_Realm_Group_By_Path_PathMCPTool - included in same package
// Import Get_Realm_Users_Management_PermissionsMCPTool - included in same package
// Import PostMCPTool - included in same package
// Import Put_Realm_Default_Groups_Group_IdMCPTool - included in same package
// Import Delete_Realm_Default_Groups_Group_IdMCPTool - included in same package
// Import Get_Realm_Default_GroupsMCPTool - included in same package
// Import Get_Realm_Events_ConfigMCPTool - included in same package
// Import Put_Realm_Default_Default_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_Admin_EventsMCPTool - included in same package
// Import Delete_Realm_Sessions_SessionMCPTool - included in same package
// Import Post_Realm_Partial_ImportMCPTool - included in same package
// Import Put_Realm_Events_ConfigMCPTool - included in same package
// Import Put_RealmMCPTool - included in same package
// Import Get_Realm_Client_Session_StatsMCPTool - included in same package
// Import Delete_Realm_EventsMCPTool - included in same package
// Import Put_Realm_Users_Management_PermissionsMCPTool - included in same package
// Import Delete_Realm_Default_Default_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_Default_Optional_Client_ScopesMCPTool - included in same package
// Import Post_Realm_Clear_User_CacheMCPTool - included in same package
// Import Get_RealmMCPTool - included in same package
// Import Delete_Realm_Admin_EventsMCPTool - included in same package
// Import Post_Realm_Clear_Keys_CacheMCPTool - included in same package
// Import Post_Realm_Clear_Realm_CacheMCPTool - included in same package
// Import Post_Realm_Push_RevocationMCPTool - included in same package
// Import Post_Realm_Test_Ldap_ConnectionMCPTool - included in same package
// Import Post_Realm_Test_Smtp_ConnectionMCPTool - included in same package
// Import Delete_Realm_Default_Optional_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_Credential_RegistratorsMCPTool - included in same package
// Import Get_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenMCPTool - included in same package
// Import Get_Realm_Clients_Id_Offline_Session_CountMCPTool - included in same package
// Import Put_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Post_Realm_Clients_Id_NodesMCPTool - included in same package
// Import Put_Realm_Clients_IdMCPTool - included in same package
// Import Get_Realm_Clients_Id_Offline_SessionsMCPTool - included in same package
// Import Post_Realm_Clients_Id_Client_SecretMCPTool - included in same package
// Import Put_Realm_Clients_Id_Management_PermissionsMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Nodes_NodeMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_ClientsMCPTool - included in same package
// Import Post_Realm_Clients_Id_Push_RevocationMCPTool - included in same package
// Import Get_Realm_Clients_Id_Evaluate_Scopes_Protocol_MappersMCPTool - included in same package
// Import Get_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_GrantedMCPTool - included in same package
// Import Get_Realm_Clients_Id_Service_Account_UserMCPTool - included in same package
// Import Delete_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_Not_GrantedMCPTool - included in same package
// Import Get_Realm_Clients_Id_Client_SecretMCPTool - included in same package
// Import Get_Realm_Clients_IdMCPTool - included in same package
// Import Get_Realm_Clients_Id_Test_Nodes_AvailableMCPTool - included in same package
// Import Get_Realm_Clients_Id_Optional_Client_ScopesMCPTool - included in same package
// Import Post_Realm_ClientsMCPTool - included in same package
// Import Post_Realm_Clients_Id_Registration_Access_TokenMCPTool - included in same package
// Import Get_Realm_Clients_Id_Installation_Providers_Provider_IdMCPTool - included in same package
// Import Get_Realm_Clients_Id_Session_CountMCPTool - included in same package
// Import Get_Realm_Clients_Id_Management_PermissionsMCPTool - included in same package
// Import Delete_Realm_Clients_IdMCPTool - included in same package
// Import Put_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdMCPTool - included in same package
// Import Get_Realm_Clients_Id_Default_Client_ScopesMCPTool - included in same package
// Import Get_Realm_Clients_Id_User_SessionsMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_Clients_Client_CompositeMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Post_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_Clients_Client_AvailableMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_Clients_Client_AvailableMCPTool - included in same package
// Import Get_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Post_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Delete_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool - included in same package
// Import Get_Realm_Users_Id_Role_Mappings_Clients_Client_CompositeMCPTool - included in same package

public class MCPServer {
    
    // Common classes that all tool classes use
    public static class APIConfig {
        private final String baseUrl;
        private final String apiKey;
        
        public APIConfig(String baseUrl, String apiKey) {
            this.baseUrl = baseUrl;
            this.apiKey = apiKey;
        }
        
        public String getBaseUrl() { return baseUrl; }
        public String getApiKey() { return apiKey; }
    }
    
    public static class MCPRequest {
        private Map<String, Object> params;
        
        public Map<String, Object> getParams() { return params; }
        public void setParams(Map<String, Object> params) { this.params = params; }
        
        @SuppressWarnings("unchecked")
        public Map<String, Object> getArguments() {
            if (params != null && params.containsKey("arguments")) {
                return (Map<String, Object>) params.get("arguments");
            }
            return new HashMap<>();
        }
    }
    
    public static class MCPToolResult {
        private final String content;
        private final boolean isError;
        
        public MCPToolResult(String content, boolean isError) {
            this.content = content;
            this.isError = isError;
        }
        
        public MCPToolResult(String content) {
            this(content, false);
        }
        
        public String getContent() { return content; }
        public boolean isError() { return isError; }
    }
    
    public static class ToolDefinition {
        private final String name;
        private final String description;
        private final Map<String, Object> parameters;
        
        public ToolDefinition(String name, String description, Map<String, Object> parameters) {
            this.name = name;
            this.description = description;
            this.parameters = parameters;
        }
        
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Map<String, Object> getParameters() { return parameters; }
    }
    
    public static class Tool {
        private final ToolDefinition definition;
        private final Function<MCPRequest, MCPToolResult> handler;
        
        public Tool(ToolDefinition definition, Function<MCPRequest, MCPToolResult> handler) {
            this.definition = definition;
            this.handler = handler;
        }
        
        public ToolDefinition getDefinition() { return definition; }
        public Function<MCPRequest, MCPToolResult> getHandler() { return handler; }
    }
    
    private static final ObjectMapper mapper = new ObjectMapper();
    private static List<Tool> tools = new ArrayList<>();
    private static APIConfig config;
    
    public static void main(String[] args) {
        try {
            // Initialize configuration
            String baseUrl = System.getenv("API_BASE_URL");
            String apiKey = System.getenv("API_KEY");
            
            if (baseUrl == null || apiKey == null) {
                System.err.println("Error: Please set API_BASE_URL and API_KEY environment variables");
                System.exit(1);
            }
            
            config = new APIConfig(baseUrl, apiKey);
            
            // Register all tools
            tools = Arrays.asList(
            Get_Realm_Client_Registration_Policy_ProvidersMCPTool.createGet_Realm_Client_Registration_Policy_ProvidersTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_CompositesTool(config),
            Get_Realm_Roles_Role_Name_UsersMCPTool.createGet_Realm_Roles_Role_Name_UsersTool(config),
            Get_Realm_Roles_Role_Name_GroupsMCPTool.createGet_Realm_Roles_Role_Name_GroupsTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_Composites_Clients_ClientMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_Composites_Clients_ClientTool(config),
            Delete_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool.createDelete_Realm_Clients_Id_Roles_Role_Name_CompositesTool(config),
            Get_Realm_RolesMCPTool.createGet_Realm_RolesTool(config),
            Get_Realm_Clients_Id_Roles_Role_NameMCPTool.createGet_Realm_Clients_Id_Roles_Role_NameTool(config),
            Post_Realm_Roles_Role_Name_CompositesMCPTool.createPost_Realm_Roles_Role_Name_CompositesTool(config),
            Get_Realm_Roles_Role_NameMCPTool.createGet_Realm_Roles_Role_NameTool(config),
            Put_Realm_Roles_Role_Name_Management_PermissionsMCPTool.createPut_Realm_Roles_Role_Name_Management_PermissionsTool(config),
            Get_Realm_Roles_Role_Name_Composites_Clients_ClientMCPTool.createGet_Realm_Roles_Role_Name_Composites_Clients_ClientTool(config),
            Put_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsMCPTool.createPut_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsTool(config),
            Post_Realm_Clients_Id_Roles_Role_Name_CompositesMCPTool.createPost_Realm_Clients_Id_Roles_Role_Name_CompositesTool(config),
            Get_Realm_Roles_Role_Name_CompositesMCPTool.createGet_Realm_Roles_Role_Name_CompositesTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_GroupsMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_GroupsTool(config),
            Put_Realm_Clients_Id_Roles_Role_NameMCPTool.createPut_Realm_Clients_Id_Roles_Role_NameTool(config),
            Put_Realm_Roles_Role_NameMCPTool.createPut_Realm_Roles_Role_NameTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_Composites_RealmMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_Composites_RealmTool(config),
            Delete_Realm_Clients_Id_Roles_Role_NameMCPTool.createDelete_Realm_Clients_Id_Roles_Role_NameTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_Management_PermissionsTool(config),
            Delete_Realm_Roles_Role_NameMCPTool.createDelete_Realm_Roles_Role_NameTool(config),
            Get_Realm_Clients_Id_RolesMCPTool.createGet_Realm_Clients_Id_RolesTool(config),
            Get_Realm_Roles_Role_Name_Management_PermissionsMCPTool.createGet_Realm_Roles_Role_Name_Management_PermissionsTool(config),
            Post_Realm_Clients_Id_RolesMCPTool.createPost_Realm_Clients_Id_RolesTool(config),
            Get_Realm_Clients_Id_Roles_Role_Name_UsersMCPTool.createGet_Realm_Clients_Id_Roles_Role_Name_UsersTool(config),
            Get_Realm_Roles_Role_Name_Composites_RealmMCPTool.createGet_Realm_Roles_Role_Name_Composites_RealmTool(config),
            Delete_Realm_Roles_Role_Name_CompositesMCPTool.createDelete_Realm_Roles_Role_Name_CompositesTool(config),
            Post_Realm_RolesMCPTool.createPost_Realm_RolesTool(config),
            Post_Realm_User_Storage_Parent_Id_Mappers_Id_SyncMCPTool.createPost_Realm_User_Storage_Parent_Id_Mappers_Id_SyncTool(config),
            Post_Realm_User_Storage_Id_Remove_Imported_UsersMCPTool.createPost_Realm_User_Storage_Id_Remove_Imported_UsersTool(config),
            Post_Realm_User_Storage_Id_SyncMCPTool.createPost_Realm_User_Storage_Id_SyncTool(config),
            Get_Id_NameMCPTool.createGet_Id_NameTool(config),
            Post_Realm_User_Storage_Id_Unlink_UsersMCPTool.createPost_Realm_User_Storage_Id_Unlink_UsersTool(config),
            Get_Realm_User_Storage_Id_NameMCPTool.createGet_Realm_User_Storage_Id_NameTool(config),
            Put_Realm_Roles_By_Id_Role_Id_Management_PermissionsMCPTool.createPut_Realm_Roles_By_Id_Role_Id_Management_PermissionsTool(config),
            Get_Realm_Roles_By_Id_Role_IdMCPTool.createGet_Realm_Roles_By_Id_Role_IdTool(config),
            Delete_Realm_Roles_By_Id_Role_Id_CompositesMCPTool.createDelete_Realm_Roles_By_Id_Role_Id_CompositesTool(config),
            Get_Realm_Roles_By_Id_Role_Id_Composites_Clients_ClientMCPTool.createGet_Realm_Roles_By_Id_Role_Id_Composites_Clients_ClientTool(config),
            Put_Realm_Roles_By_Id_Role_IdMCPTool.createPut_Realm_Roles_By_Id_Role_IdTool(config),
            Get_Realm_Roles_By_Id_Role_Id_Management_PermissionsMCPTool.createGet_Realm_Roles_By_Id_Role_Id_Management_PermissionsTool(config),
            Delete_Realm_Roles_By_Id_Role_IdMCPTool.createDelete_Realm_Roles_By_Id_Role_IdTool(config),
            Get_Realm_Roles_By_Id_Role_Id_Composites_RealmMCPTool.createGet_Realm_Roles_By_Id_Role_Id_Composites_RealmTool(config),
            Post_Realm_Roles_By_Id_Role_Id_CompositesMCPTool.createPost_Realm_Roles_By_Id_Role_Id_CompositesTool(config),
            Get_Realm_Roles_By_Id_Role_Id_CompositesMCPTool.createGet_Realm_Roles_By_Id_Role_Id_CompositesTool(config),
            Get_Realm_KeysMCPTool.createGet_Realm_KeysTool(config),
            GetMCPTool.createGetTool(config),
            Delete_Realm_Attack_Detection_Brute_Force_Users_User_IdMCPTool.createDelete_Realm_Attack_Detection_Brute_Force_Users_User_IdTool(config),
            Delete_Realm_Attack_Detection_Brute_Force_UsersMCPTool.createDelete_Realm_Attack_Detection_Brute_Force_UsersTool(config),
            Get_Realm_Attack_Detection_Brute_Force_Users_User_IdMCPTool.createGet_Realm_Attack_Detection_Brute_Force_Users_User_IdTool(config),
            Get_Realm_Users_Id_Groups_CountMCPTool.createGet_Realm_Users_Id_Groups_CountTool(config),
            Delete_Realm_Users_IdMCPTool.createDelete_Realm_Users_IdTool(config),
            Delete_Realm_Users_Id_Groups_Group_IdMCPTool.createDelete_Realm_Users_Id_Groups_Group_IdTool(config),
            Get_Realm_UsersMCPTool.createGet_Realm_UsersTool(config),
            Put_Realm_Users_Id_Groups_Group_IdMCPTool.createPut_Realm_Users_Id_Groups_Group_IdTool(config),
            Put_Realm_Users_Id_Disable_Credential_TypesMCPTool.createPut_Realm_Users_Id_Disable_Credential_TypesTool(config),
            Get_Realm_Users_Id_Configured_User_Storage_Credential_TypesMCPTool.createGet_Realm_Users_Id_Configured_User_Storage_Credential_TypesTool(config),
            Delete_Realm_Users_Id_Consents_ClientMCPTool.createDelete_Realm_Users_Id_Consents_ClientTool(config),
            Put_Realm_Users_Id_Credentials_Credential_Id_User_LabelMCPTool.createPut_Realm_Users_Id_Credentials_Credential_Id_User_LabelTool(config),
            Put_Realm_Users_Id_Send_Verify_EmailMCPTool.createPut_Realm_Users_Id_Send_Verify_EmailTool(config),
            Get_Realm_Users_IdMCPTool.createGet_Realm_Users_IdTool(config),
            Get_Realm_Users_CountMCPTool.createGet_Realm_Users_CountTool(config),
            Get_Realm_Users_Id_ConsentsMCPTool.createGet_Realm_Users_Id_ConsentsTool(config),
            Post_Realm_UsersMCPTool.createPost_Realm_UsersTool(config),
            Get_Realm_Users_Id_Federated_IdentityMCPTool.createGet_Realm_Users_Id_Federated_IdentityTool(config),
            Post_Realm_Users_Id_LogoutMCPTool.createPost_Realm_Users_Id_LogoutTool(config),
            Delete_Realm_Users_Id_Credentials_Credential_IdMCPTool.createDelete_Realm_Users_Id_Credentials_Credential_IdTool(config),
            Get_Realm_Users_Id_SessionsMCPTool.createGet_Realm_Users_Id_SessionsTool(config),
            Get_Realm_Users_Id_Offline_Sessions_Client_IdMCPTool.createGet_Realm_Users_Id_Offline_Sessions_Client_IdTool(config),
            Post_Realm_Users_Id_Federated_Identity_ProviderMCPTool.createPost_Realm_Users_Id_Federated_Identity_ProviderTool(config),
            Get_Realm_Users_Id_GroupsMCPTool.createGet_Realm_Users_Id_GroupsTool(config),
            Delete_Realm_Users_Id_Federated_Identity_ProviderMCPTool.createDelete_Realm_Users_Id_Federated_Identity_ProviderTool(config),
            Get_Realm_Users_Id_CredentialsMCPTool.createGet_Realm_Users_Id_CredentialsTool(config),
            Put_Realm_Users_IdMCPTool.createPut_Realm_Users_IdTool(config),
            Put_Realm_Users_Id_Reset_PasswordMCPTool.createPut_Realm_Users_Id_Reset_PasswordTool(config),
            Post_Realm_Users_Id_ImpersonationMCPTool.createPost_Realm_Users_Id_ImpersonationTool(config),
            Post_Realm_Users_Id_Credentials_Credential_Id_Move_To_FirstMCPTool.createPost_Realm_Users_Id_Credentials_Credential_Id_Move_To_FirstTool(config),
            Put_Realm_Users_Id_Execute_Actions_EmailMCPTool.createPut_Realm_Users_Id_Execute_Actions_EmailTool(config),
            Post_Realm_Users_Id_Credentials_Credential_Id_Move_After_New_Previous_Credential_IdMCPTool.createPost_Realm_Users_Id_Credentials_Credential_Id_Move_After_New_Previous_Credential_IdTool(config),
            Post_Realm_Users_Id_Role_Mappings_RealmMCPTool.createPost_Realm_Users_Id_Role_Mappings_RealmTool(config),
            Delete_Realm_Groups_Id_Role_Mappings_RealmMCPTool.createDelete_Realm_Groups_Id_Role_Mappings_RealmTool(config),
            Get_Realm_Groups_Id_Role_Mappings_RealmMCPTool.createGet_Realm_Groups_Id_Role_Mappings_RealmTool(config),
            Get_Realm_Users_Id_Role_MappingsMCPTool.createGet_Realm_Users_Id_Role_MappingsTool(config),
            Post_Realm_Groups_Id_Role_Mappings_RealmMCPTool.createPost_Realm_Groups_Id_Role_Mappings_RealmTool(config),
            Get_Realm_Users_Id_Role_Mappings_RealmMCPTool.createGet_Realm_Users_Id_Role_Mappings_RealmTool(config),
            Get_Realm_Groups_Id_Role_Mappings_Realm_CompositeMCPTool.createGet_Realm_Groups_Id_Role_Mappings_Realm_CompositeTool(config),
            Get_Realm_Users_Id_Role_Mappings_Realm_AvailableMCPTool.createGet_Realm_Users_Id_Role_Mappings_Realm_AvailableTool(config),
            Get_Realm_Users_Id_Role_Mappings_Realm_CompositeMCPTool.createGet_Realm_Users_Id_Role_Mappings_Realm_CompositeTool(config),
            Get_Realm_Groups_Id_Role_MappingsMCPTool.createGet_Realm_Groups_Id_Role_MappingsTool(config),
            Get_Realm_Groups_Id_Role_Mappings_Realm_AvailableMCPTool.createGet_Realm_Groups_Id_Role_Mappings_Realm_AvailableTool(config),
            Delete_Realm_Users_Id_Role_Mappings_RealmMCPTool.createDelete_Realm_Users_Id_Role_Mappings_RealmTool(config),
            Put_Realm_Groups_IdMCPTool.createPut_Realm_Groups_IdTool(config),
            Put_Realm_Groups_Id_Management_PermissionsMCPTool.createPut_Realm_Groups_Id_Management_PermissionsTool(config),
            Get_Realm_Groups_Id_MembersMCPTool.createGet_Realm_Groups_Id_MembersTool(config),
            Delete_Realm_Groups_IdMCPTool.createDelete_Realm_Groups_IdTool(config),
            Post_Realm_GroupsMCPTool.createPost_Realm_GroupsTool(config),
            Get_Realm_Groups_IdMCPTool.createGet_Realm_Groups_IdTool(config),
            Get_Realm_Groups_CountMCPTool.createGet_Realm_Groups_CountTool(config),
            Get_Realm_Groups_Id_Management_PermissionsMCPTool.createGet_Realm_Groups_Id_Management_PermissionsTool(config),
            Post_Realm_Groups_Id_ChildrenMCPTool.createPost_Realm_Groups_Id_ChildrenTool(config),
            Get_Realm_GroupsMCPTool.createGet_Realm_GroupsTool(config),
            Post_Realm_Authentication_Executions_Execution_Id_Raise_PriorityMCPTool.createPost_Realm_Authentication_Executions_Execution_Id_Raise_PriorityTool(config),
            Get_Realm_Authentication_Unregistered_Required_ActionsMCPTool.createGet_Realm_Authentication_Unregistered_Required_ActionsTool(config),
            Get_Realm_Authentication_Per_Client_Config_DescriptionMCPTool.createGet_Realm_Authentication_Per_Client_Config_DescriptionTool(config),
            Post_Realm_Authentication_FlowsMCPTool.createPost_Realm_Authentication_FlowsTool(config),
            Delete_Realm_Authentication_Flows_IdMCPTool.createDelete_Realm_Authentication_Flows_IdTool(config),
            Put_Realm_Authentication_Flows_Flow_Alias_ExecutionsMCPTool.createPut_Realm_Authentication_Flows_Flow_Alias_ExecutionsTool(config),
            Get_Realm_Authentication_Form_Action_ProvidersMCPTool.createGet_Realm_Authentication_Form_Action_ProvidersTool(config),
            Put_Realm_Authentication_Flows_IdMCPTool.createPut_Realm_Authentication_Flows_IdTool(config),
            Get_Realm_Authentication_Config_Description_Provider_IdMCPTool.createGet_Realm_Authentication_Config_Description_Provider_IdTool(config),
            Get_Realm_Authentication_Client_Authenticator_ProvidersMCPTool.createGet_Realm_Authentication_Client_Authenticator_ProvidersTool(config),
            Get_Realm_Authentication_FlowsMCPTool.createGet_Realm_Authentication_FlowsTool(config),
            Delete_Realm_Authentication_Config_IdMCPTool.createDelete_Realm_Authentication_Config_IdTool(config),
            Get_Realm_Authentication_Required_Actions_AliasMCPTool.createGet_Realm_Authentication_Required_Actions_AliasTool(config),
            Get_Realm_Authentication_Config_IdMCPTool.createGet_Realm_Authentication_Config_IdTool(config),
            Get_Realm_Authentication_Required_ActionsMCPTool.createGet_Realm_Authentication_Required_ActionsTool(config),
            Get_Realm_Authentication_Flows_Flow_Alias_ExecutionsMCPTool.createGet_Realm_Authentication_Flows_Flow_Alias_ExecutionsTool(config),
            Post_Realm_Authentication_Required_Actions_Alias_Raise_PriorityMCPTool.createPost_Realm_Authentication_Required_Actions_Alias_Raise_PriorityTool(config),
            Post_Realm_Authentication_Flows_Flow_Alias_Executions_ExecutionMCPTool.createPost_Realm_Authentication_Flows_Flow_Alias_Executions_ExecutionTool(config),
            Post_Realm_Authentication_ExecutionsMCPTool.createPost_Realm_Authentication_ExecutionsTool(config),
            Post_Realm_Authentication_Executions_Execution_Id_ConfigMCPTool.createPost_Realm_Authentication_Executions_Execution_Id_ConfigTool(config),
            Get_Realm_Authentication_Flows_IdMCPTool.createGet_Realm_Authentication_Flows_IdTool(config),
            Get_Realm_Authentication_Form_ProvidersMCPTool.createGet_Realm_Authentication_Form_ProvidersTool(config),
            Get_Realm_Authentication_Authenticator_ProvidersMCPTool.createGet_Realm_Authentication_Authenticator_ProvidersTool(config),
            Get_Realm_Authentication_Executions_Execution_IdMCPTool.createGet_Realm_Authentication_Executions_Execution_IdTool(config),
            Post_Realm_Authentication_Flows_Flow_Alias_Executions_FlowMCPTool.createPost_Realm_Authentication_Flows_Flow_Alias_Executions_FlowTool(config),
            Post_Realm_Authentication_Flows_Flow_Alias_CopyMCPTool.createPost_Realm_Authentication_Flows_Flow_Alias_CopyTool(config),
            Post_Realm_Authentication_Required_Actions_Alias_Lower_PriorityMCPTool.createPost_Realm_Authentication_Required_Actions_Alias_Lower_PriorityTool(config),
            Put_Realm_Authentication_Config_IdMCPTool.createPut_Realm_Authentication_Config_IdTool(config),
            Delete_Realm_Authentication_Required_Actions_AliasMCPTool.createDelete_Realm_Authentication_Required_Actions_AliasTool(config),
            Post_Realm_Authentication_Executions_Execution_Id_Lower_PriorityMCPTool.createPost_Realm_Authentication_Executions_Execution_Id_Lower_PriorityTool(config),
            Delete_Realm_Authentication_Executions_Execution_IdMCPTool.createDelete_Realm_Authentication_Executions_Execution_IdTool(config),
            Post_Realm_Authentication_Register_Required_ActionMCPTool.createPost_Realm_Authentication_Register_Required_ActionTool(config),
            Put_Realm_Authentication_Required_Actions_AliasMCPTool.createPut_Realm_Authentication_Required_Actions_AliasTool(config),
            Post_Realm_ComponentsMCPTool.createPost_Realm_ComponentsTool(config),
            Get_Realm_Components_Id_Sub_Component_TypesMCPTool.createGet_Realm_Components_Id_Sub_Component_TypesTool(config),
            Get_Realm_ComponentsMCPTool.createGet_Realm_ComponentsTool(config),
            Get_Realm_Components_IdMCPTool.createGet_Realm_Components_IdTool(config),
            Put_Realm_Components_IdMCPTool.createPut_Realm_Components_IdTool(config),
            Delete_Realm_Components_IdMCPTool.createDelete_Realm_Components_IdTool(config),
            Put_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool.createPut_Realm_Clients_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Delete_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool.createDelete_Realm_Clients_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Put_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool.createPut_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Get_Realm_Clients_Id1_Protocol_Mappers_Models_Id2MCPTool.createGet_Realm_Clients_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Get_Realm_Clients_Id_Protocol_Mappers_ModelsMCPTool.createGet_Realm_Clients_Id_Protocol_Mappers_ModelsTool(config),
            Post_Realm_Clients_Id_Protocol_Mappers_Add_ModelsMCPTool.createPost_Realm_Clients_Id_Protocol_Mappers_Add_ModelsTool(config),
            Get_Realm_Clients_Id_Protocol_Mappers_Protocol_ProtocolMCPTool.createGet_Realm_Clients_Id_Protocol_Mappers_Protocol_ProtocolTool(config),
            Post_Realm_Client_Scopes_Id_Protocol_Mappers_Add_ModelsMCPTool.createPost_Realm_Client_Scopes_Id_Protocol_Mappers_Add_ModelsTool(config),
            Delete_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool.createDelete_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Get_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2MCPTool.createGet_Realm_Client_Scopes_Id1_Protocol_Mappers_Models_Id2Tool(config),
            Post_Realm_Clients_Id_Protocol_Mappers_ModelsMCPTool.createPost_Realm_Clients_Id_Protocol_Mappers_ModelsTool(config),
            Post_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsMCPTool.createPost_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsTool(config),
            Get_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsMCPTool.createGet_Realm_Client_Scopes_Id_Protocol_Mappers_ModelsTool(config),
            Get_Realm_Client_Scopes_Id_Protocol_Mappers_Protocol_ProtocolMCPTool.createGet_Realm_Client_Scopes_Id_Protocol_Mappers_Protocol_ProtocolTool(config),
            Delete_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool.createDelete_Realm_Identity_Provider_Instances_Alias_Mappers_IdTool(config),
            Put_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool.createPut_Realm_Identity_Provider_Instances_Alias_Mappers_IdTool(config),
            Get_Realm_Identity_Provider_Providers_Provider_IdMCPTool.createGet_Realm_Identity_Provider_Providers_Provider_IdTool(config),
            Post_Realm_Identity_Provider_InstancesMCPTool.createPost_Realm_Identity_Provider_InstancesTool(config),
            Get_Realm_Identity_Provider_InstancesMCPTool.createGet_Realm_Identity_Provider_InstancesTool(config),
            Get_Realm_Identity_Provider_Instances_AliasMCPTool.createGet_Realm_Identity_Provider_Instances_AliasTool(config),
            Delete_Realm_Identity_Provider_Instances_AliasMCPTool.createDelete_Realm_Identity_Provider_Instances_AliasTool(config),
            Put_Realm_Identity_Provider_Instances_AliasMCPTool.createPut_Realm_Identity_Provider_Instances_AliasTool(config),
            Get_Realm_Identity_Provider_Instances_Alias_ExportMCPTool.createGet_Realm_Identity_Provider_Instances_Alias_ExportTool(config),
            Get_Realm_Identity_Provider_Instances_Alias_MappersMCPTool.createGet_Realm_Identity_Provider_Instances_Alias_MappersTool(config),
            Put_Realm_Identity_Provider_Instances_Alias_Management_PermissionsMCPTool.createPut_Realm_Identity_Provider_Instances_Alias_Management_PermissionsTool(config),
            Post_Realm_Identity_Provider_Import_ConfigMCPTool.createPost_Realm_Identity_Provider_Import_ConfigTool(config),
            Get_Realm_Identity_Provider_Instances_Alias_Mappers_IdMCPTool.createGet_Realm_Identity_Provider_Instances_Alias_Mappers_IdTool(config),
            Get_Realm_Identity_Provider_Instances_Alias_Management_PermissionsMCPTool.createGet_Realm_Identity_Provider_Instances_Alias_Management_PermissionsTool(config),
            Get_Realm_Identity_Provider_Instances_Alias_Mapper_TypesMCPTool.createGet_Realm_Identity_Provider_Instances_Alias_Mapper_TypesTool(config),
            Post_Realm_Identity_Provider_Instances_Alias_MappersMCPTool.createPost_Realm_Identity_Provider_Instances_Alias_MappersTool(config),
            Get_Realm_Client_ScopesMCPTool.createGet_Realm_Client_ScopesTool(config),
            Put_Realm_Client_Scopes_IdMCPTool.createPut_Realm_Client_Scopes_IdTool(config),
            Get_Realm_Client_Scopes_IdMCPTool.createGet_Realm_Client_Scopes_IdTool(config),
            Delete_Realm_Client_Scopes_IdMCPTool.createDelete_Realm_Client_Scopes_IdTool(config),
            Post_Realm_Client_ScopesMCPTool.createPost_Realm_Client_ScopesTool(config),
            Get_Realm_Clients_Id_Certificates_AttrMCPTool.createGet_Realm_Clients_Id_Certificates_AttrTool(config),
            Post_Realm_Clients_Id_Certificates_Attr_GenerateMCPTool.createPost_Realm_Clients_Id_Certificates_Attr_GenerateTool(config),
            Post_Realm_Clients_Id_Certificates_Attr_Upload_CertificateMCPTool.createPost_Realm_Clients_Id_Certificates_Attr_Upload_CertificateTool(config),
            Post_Realm_Clients_Id_Certificates_Attr_UploadMCPTool.createPost_Realm_Clients_Id_Certificates_Attr_UploadTool(config),
            Post_Realm_Clients_Initial_AccessMCPTool.createPost_Realm_Clients_Initial_AccessTool(config),
            Delete_Realm_Clients_Initial_Access_IdMCPTool.createDelete_Realm_Clients_Initial_Access_IdTool(config),
            Get_Realm_Clients_Initial_AccessMCPTool.createGet_Realm_Clients_Initial_AccessTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_AvailableMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_AvailableTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_RealmMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_RealmTool(config),
            Post_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool.createPost_Realm_Client_Scopes_Id_Scope_Mappings_RealmTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_RealmTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_Realm_CompositeMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_Realm_CompositeTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_Clients_Client_CompositeMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_Clients_Client_CompositeTool(config),
            Get_Realm_Clients_Id_Scope_MappingsMCPTool.createGet_Realm_Clients_Id_Scope_MappingsTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_Realm_AvailableMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_Realm_AvailableTool(config),
            Delete_Realm_Client_Scopes_Id_Scope_Mappings_RealmMCPTool.createDelete_Realm_Client_Scopes_Id_Scope_Mappings_RealmTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_Realm_AvailableMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_Realm_AvailableTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_CompositeMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_Clients_Client_CompositeTool(config),
            Get_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool.createGet_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientTool(config),
            Post_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool.createPost_Realm_Clients_Id_Scope_Mappings_Clients_ClientTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_Clients_ClientTool(config),
            Delete_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool.createDelete_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientTool(config),
            Post_Realm_Clients_Id_Scope_Mappings_RealmMCPTool.createPost_Realm_Clients_Id_Scope_Mappings_RealmTool(config),
            Post_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientMCPTool.createPost_Realm_Client_Scopes_Id_Scope_Mappings_Clients_ClientTool(config),
            Delete_Realm_Clients_Id_Scope_Mappings_Clients_ClientMCPTool.createDelete_Realm_Clients_Id_Scope_Mappings_Clients_ClientTool(config),
            Delete_Realm_Clients_Id_Scope_Mappings_RealmMCPTool.createDelete_Realm_Clients_Id_Scope_Mappings_RealmTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_Realm_CompositeMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_Realm_CompositeTool(config),
            Get_Realm_Clients_Id_Scope_Mappings_Clients_Client_AvailableMCPTool.createGet_Realm_Clients_Id_Scope_Mappings_Clients_Client_AvailableTool(config),
            Get_Realm_Client_Scopes_Id_Scope_MappingsMCPTool.createGet_Realm_Client_Scopes_Id_Scope_MappingsTool(config),
            Post_Realm_Logout_AllMCPTool.createPost_Realm_Logout_AllTool(config),
            Get_Realm_Default_Default_Client_ScopesMCPTool.createGet_Realm_Default_Default_Client_ScopesTool(config),
            Post_Realm_Partial_ExportMCPTool.createPost_Realm_Partial_ExportTool(config),
            Post_Realm_Client_Description_ConverterMCPTool.createPost_Realm_Client_Description_ConverterTool(config),
            Put_Realm_Default_Optional_Client_Scopes_Client_Scope_IdMCPTool.createPut_Realm_Default_Optional_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_EventsMCPTool.createGet_Realm_EventsTool(config),
            Delete_RealmMCPTool.createDelete_RealmTool(config),
            Get_Realm_Group_By_Path_PathMCPTool.createGet_Realm_Group_By_Path_PathTool(config),
            Get_Realm_Users_Management_PermissionsMCPTool.createGet_Realm_Users_Management_PermissionsTool(config),
            PostMCPTool.createPostTool(config),
            Put_Realm_Default_Groups_Group_IdMCPTool.createPut_Realm_Default_Groups_Group_IdTool(config),
            Delete_Realm_Default_Groups_Group_IdMCPTool.createDelete_Realm_Default_Groups_Group_IdTool(config),
            Get_Realm_Default_GroupsMCPTool.createGet_Realm_Default_GroupsTool(config),
            Get_Realm_Events_ConfigMCPTool.createGet_Realm_Events_ConfigTool(config),
            Put_Realm_Default_Default_Client_Scopes_Client_Scope_IdMCPTool.createPut_Realm_Default_Default_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_Admin_EventsMCPTool.createGet_Realm_Admin_EventsTool(config),
            Delete_Realm_Sessions_SessionMCPTool.createDelete_Realm_Sessions_SessionTool(config),
            Post_Realm_Partial_ImportMCPTool.createPost_Realm_Partial_ImportTool(config),
            Put_Realm_Events_ConfigMCPTool.createPut_Realm_Events_ConfigTool(config),
            Put_RealmMCPTool.createPut_RealmTool(config),
            Get_Realm_Client_Session_StatsMCPTool.createGet_Realm_Client_Session_StatsTool(config),
            Delete_Realm_EventsMCPTool.createDelete_Realm_EventsTool(config),
            Put_Realm_Users_Management_PermissionsMCPTool.createPut_Realm_Users_Management_PermissionsTool(config),
            Delete_Realm_Default_Default_Client_Scopes_Client_Scope_IdMCPTool.createDelete_Realm_Default_Default_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_Default_Optional_Client_ScopesMCPTool.createGet_Realm_Default_Optional_Client_ScopesTool(config),
            Post_Realm_Clear_User_CacheMCPTool.createPost_Realm_Clear_User_CacheTool(config),
            Get_RealmMCPTool.createGet_RealmTool(config),
            Delete_Realm_Admin_EventsMCPTool.createDelete_Realm_Admin_EventsTool(config),
            Post_Realm_Clear_Keys_CacheMCPTool.createPost_Realm_Clear_Keys_CacheTool(config),
            Post_Realm_Clear_Realm_CacheMCPTool.createPost_Realm_Clear_Realm_CacheTool(config),
            Post_Realm_Push_RevocationMCPTool.createPost_Realm_Push_RevocationTool(config),
            Post_Realm_Test_Ldap_ConnectionMCPTool.createPost_Realm_Test_Ldap_ConnectionTool(config),
            Post_Realm_Test_Smtp_ConnectionMCPTool.createPost_Realm_Test_Smtp_ConnectionTool(config),
            Delete_Realm_Default_Optional_Client_Scopes_Client_Scope_IdMCPTool.createDelete_Realm_Default_Optional_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_Credential_RegistratorsMCPTool.createGet_Realm_Credential_RegistratorsTool(config),
            Get_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenMCPTool.createGet_Realm_Clients_Id_Evaluate_Scopes_Generate_Example_Access_TokenTool(config),
            Get_Realm_Clients_Id_Offline_Session_CountMCPTool.createGet_Realm_Clients_Id_Offline_Session_CountTool(config),
            Put_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdMCPTool.createPut_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdTool(config),
            Post_Realm_Clients_Id_NodesMCPTool.createPost_Realm_Clients_Id_NodesTool(config),
            Put_Realm_Clients_IdMCPTool.createPut_Realm_Clients_IdTool(config),
            Get_Realm_Clients_Id_Offline_SessionsMCPTool.createGet_Realm_Clients_Id_Offline_SessionsTool(config),
            Post_Realm_Clients_Id_Client_SecretMCPTool.createPost_Realm_Clients_Id_Client_SecretTool(config),
            Put_Realm_Clients_Id_Management_PermissionsMCPTool.createPut_Realm_Clients_Id_Management_PermissionsTool(config),
            Delete_Realm_Clients_Id_Nodes_NodeMCPTool.createDelete_Realm_Clients_Id_Nodes_NodeTool(config),
            Delete_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdMCPTool.createDelete_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_ClientsMCPTool.createGet_Realm_ClientsTool(config),
            Post_Realm_Clients_Id_Push_RevocationMCPTool.createPost_Realm_Clients_Id_Push_RevocationTool(config),
            Get_Realm_Clients_Id_Evaluate_Scopes_Protocol_MappersMCPTool.createGet_Realm_Clients_Id_Evaluate_Scopes_Protocol_MappersTool(config),
            Get_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_GrantedMCPTool.createGet_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_GrantedTool(config),
            Get_Realm_Clients_Id_Service_Account_UserMCPTool.createGet_Realm_Clients_Id_Service_Account_UserTool(config),
            Delete_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdMCPTool.createDelete_Realm_Clients_Id_Default_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_Not_GrantedMCPTool.createGet_Realm_Clients_Id_Evaluate_Scopes_Scope_Mappings_Role_Container_Id_Not_GrantedTool(config),
            Get_Realm_Clients_Id_Client_SecretMCPTool.createGet_Realm_Clients_Id_Client_SecretTool(config),
            Get_Realm_Clients_IdMCPTool.createGet_Realm_Clients_IdTool(config),
            Get_Realm_Clients_Id_Test_Nodes_AvailableMCPTool.createGet_Realm_Clients_Id_Test_Nodes_AvailableTool(config),
            Get_Realm_Clients_Id_Optional_Client_ScopesMCPTool.createGet_Realm_Clients_Id_Optional_Client_ScopesTool(config),
            Post_Realm_ClientsMCPTool.createPost_Realm_ClientsTool(config),
            Post_Realm_Clients_Id_Registration_Access_TokenMCPTool.createPost_Realm_Clients_Id_Registration_Access_TokenTool(config),
            Get_Realm_Clients_Id_Installation_Providers_Provider_IdMCPTool.createGet_Realm_Clients_Id_Installation_Providers_Provider_IdTool(config),
            Get_Realm_Clients_Id_Session_CountMCPTool.createGet_Realm_Clients_Id_Session_CountTool(config),
            Get_Realm_Clients_Id_Management_PermissionsMCPTool.createGet_Realm_Clients_Id_Management_PermissionsTool(config),
            Delete_Realm_Clients_IdMCPTool.createDelete_Realm_Clients_IdTool(config),
            Put_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdMCPTool.createPut_Realm_Clients_Id_Optional_Client_Scopes_Client_Scope_IdTool(config),
            Get_Realm_Clients_Id_Default_Client_ScopesMCPTool.createGet_Realm_Clients_Id_Default_Client_ScopesTool(config),
            Get_Realm_Clients_Id_User_SessionsMCPTool.createGet_Realm_Clients_Id_User_SessionsTool(config),
            Get_Realm_Groups_Id_Role_Mappings_Clients_Client_CompositeMCPTool.createGet_Realm_Groups_Id_Role_Mappings_Clients_Client_CompositeTool(config),
            Get_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool.createGet_Realm_Users_Id_Role_Mappings_Clients_ClientTool(config),
            Post_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool.createPost_Realm_Users_Id_Role_Mappings_Clients_ClientTool(config),
            Get_Realm_Groups_Id_Role_Mappings_Clients_Client_AvailableMCPTool.createGet_Realm_Groups_Id_Role_Mappings_Clients_Client_AvailableTool(config),
            Get_Realm_Users_Id_Role_Mappings_Clients_Client_AvailableMCPTool.createGet_Realm_Users_Id_Role_Mappings_Clients_Client_AvailableTool(config),
            Get_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool.createGet_Realm_Groups_Id_Role_Mappings_Clients_ClientTool(config),
            Delete_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool.createDelete_Realm_Groups_Id_Role_Mappings_Clients_ClientTool(config),
            Post_Realm_Groups_Id_Role_Mappings_Clients_ClientMCPTool.createPost_Realm_Groups_Id_Role_Mappings_Clients_ClientTool(config),
            Delete_Realm_Users_Id_Role_Mappings_Clients_ClientMCPTool.createDelete_Realm_Users_Id_Role_Mappings_Clients_ClientTool(config),
            Get_Realm_Users_Id_Role_Mappings_Clients_Client_CompositeMCPTool.createGet_Realm_Users_Id_Role_Mappings_Clients_Client_CompositeTool(config)
            );
            
            System.err.println("MCP Server started with " + tools.size() + " tools");
            
            // Start JSON-RPC server
            runServer();
            
        } catch (Exception e) {
            System.err.println("Failed to start MCP server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void runServer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while ((line = reader.readLine()) != null) {
            JsonNode request = null;
            try {
                request = mapper.readTree(line);
                JsonNode response = handleRequest(request);
                
                if (response != null) {
                    System.out.println(mapper.writeValueAsString(response));
                }
                
            } catch (Exception e) {
                // Send error response
                ObjectNode errorResponse = mapper.createObjectNode();
                errorResponse.put("jsonrpc", "2.0");
                if (request != null && request.has("id")) {
                    errorResponse.put("id", request.get("id").asText());
                } else {
                    errorResponse.putNull("id");
                }
                
                ObjectNode error = mapper.createObjectNode();
                error.put("code", -32603);
                error.put("message", "Internal error: " + e.getMessage());
                errorResponse.set("error", error);
                
                System.out.println(mapper.writeValueAsString(errorResponse));
            }
        }
    }
    
    private static JsonNode handleRequest(JsonNode request) {
        if (!request.has("method")) {
            return createErrorResponse(request, -32600, "Invalid Request");
        }
        
        String method = request.get("method").asText();
        JsonNode params = request.get("params");
        String id = request.has("id") ? request.get("id").asText() : null;
        
        switch (method) {
            case "initialize":
                return handleInitialize(id);
            case "tools/list":
                return handleToolsList(id);
            case "tools/call":
                return handleToolsCall(id, params);
            default:
                return createErrorResponse(request, -32601, "Method not found");
        }
    }
    
    private static JsonNode handleInitialize(String id) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", id);
        
        ObjectNode result = mapper.createObjectNode();
        result.put("protocolVersion", "2024-11-05");
        
        ObjectNode capabilities = mapper.createObjectNode();
        ObjectNode toolsCapability = mapper.createObjectNode();
        toolsCapability.put("listChanged", false);
        capabilities.set("tools", toolsCapability);
        result.set("capabilities", capabilities);
        
        ObjectNode serverInfo = mapper.createObjectNode();
        serverInfo.put("name", "Opsera MCP Server");
        serverInfo.put("version", "1.0.0");
        result.set("serverInfo", serverInfo);
        
        response.set("result", result);
        return response;
    }
    
    private static JsonNode handleToolsList(String id) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", id);
        
        ObjectNode result = mapper.createObjectNode();
        ArrayNode toolsArray = mapper.createArrayNode();
        
        for (Tool tool : tools) {
            ObjectNode toolDef = mapper.createObjectNode();
            toolDef.put("name", tool.getDefinition().getName());
            toolDef.put("description", tool.getDefinition().getDescription());
            
            // Convert parameters to JSON
            ObjectNode inputSchema = mapper.valueToTree(tool.getDefinition().getParameters());
            toolDef.set("inputSchema", inputSchema);
            
            toolsArray.add(toolDef);
        }
        
        result.set("tools", toolsArray);
        response.set("result", result);
        return response;
    }
    
    private static JsonNode handleToolsCall(String id, JsonNode params) {
        if (!params.has("name")) {
            return createErrorResponse(null, -32602, "Invalid params: missing 'name'");
        }
        
        String toolName = params.get("name").asText();
        JsonNode arguments = params.has("arguments") ? params.get("arguments") : mapper.createObjectNode();
        
        // Find the tool
        Tool tool = null;
        for (Tool t : tools) {
            if (t.getDefinition().getName().equals(toolName)) {
                tool = t;
                break;
            }
        }
        
        if (tool == null) {
            return createErrorResponse(null, -32602, "Tool not found: " + toolName);
        }
        
        try {
            // Convert arguments to Map
            Map<String, Object> argsMap = mapper.convertValue(arguments, Map.class);
            
            // Create MCP request
            MCPRequest mcpRequest = new MCPRequest();
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("arguments", argsMap);
            mcpRequest.setParams(requestParams);
            
            // Execute tool
            MCPToolResult result = tool.getHandler().apply(mcpRequest);
            
            // Create response
            ObjectNode response = mapper.createObjectNode();
            response.put("jsonrpc", "2.0");
            response.put("id", id);
            
            ObjectNode resultObj = mapper.createObjectNode();
            ArrayNode content = mapper.createArrayNode();
            
            ObjectNode textContent = mapper.createObjectNode();
            textContent.put("type", "text");
            textContent.put("text", result.getContent());
            content.add(textContent);
            
            resultObj.set("content", content);
            resultObj.put("isError", result.isError());
            
            response.set("result", resultObj);
            return response;
            
        } catch (Exception e) {
            return createErrorResponse(null, -32603, "Tool execution failed: " + e.getMessage());
        }
    }
    
    private static JsonNode createErrorResponse(JsonNode request, int code, String message) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", request != null && request.has("id") ? request.get("id").asText() : null);
        
        ObjectNode error = mapper.createObjectNode();
        error.put("code", code);
        error.put("message", message);
        response.set("error", error);
        
        return response;
    }
}