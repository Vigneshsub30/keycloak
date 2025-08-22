package models

import (
	"context"
	"github.com/mark3labs/mcp-go/mcp"
)

type Tool struct {
	Definition mcp.Tool
	Handler    func(ctx context.Context, req mcp.CallToolRequest) (*mcp.CallToolResult, error)
}

// SynchronizationResult represents the SynchronizationResult schema from the OpenAPI specification
type SynchronizationResult struct {
	Status string `json:"status,omitempty"`
	Updated int `json:"updated,omitempty"`
	Added int `json:"added,omitempty"`
	Failed int `json:"failed,omitempty"`
	Ignored bool `json:"ignored,omitempty"`
	Removed int `json:"removed,omitempty"`
}

// UserSessionRepresentation represents the UserSessionRepresentation schema from the OpenAPI specification
type UserSessionRepresentation struct {
	Lastaccess int64 `json:"lastAccess,omitempty"`
	Start int64 `json:"start,omitempty"`
	Userid string `json:"userId,omitempty"`
	Username string `json:"username,omitempty"`
	Clients map[string]interface{} `json:"clients,omitempty"`
	Id string `json:"id,omitempty"`
	Ipaddress string `json:"ipAddress,omitempty"`
}

// ScopeRepresentation represents the ScopeRepresentation schema from the OpenAPI specification
type ScopeRepresentation struct {
	Displayname string `json:"displayName,omitempty"`
	Iconuri string `json:"iconUri,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Policies []PolicyRepresentation `json:"policies,omitempty"`
	Resources []ResourceRepresentation `json:"resources,omitempty"`
}

// ComponentExportRepresentation represents the ComponentExportRepresentation schema from the OpenAPI specification
type ComponentExportRepresentation struct {
	Name string `json:"name,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Subcomponents MultivaluedHashMap `json:"subComponents,omitempty"`
	Subtype string `json:"subType,omitempty"`
	Config MultivaluedHashMap `json:"config,omitempty"`
	Id string `json:"id,omitempty"`
}

// PartialImportRepresentation represents the PartialImportRepresentation schema from the OpenAPI specification
type PartialImportRepresentation struct {
	Policy string `json:"policy,omitempty"`
	Roles RolesRepresentation `json:"roles,omitempty"`
	Users []UserRepresentation `json:"users,omitempty"`
	Clients []ClientRepresentation `json:"clients,omitempty"`
	Groups []GroupRepresentation `json:"groups,omitempty"`
	Identityproviders []IdentityProviderRepresentation `json:"identityProviders,omitempty"`
	Ifresourceexists string `json:"ifResourceExists,omitempty"`
}

// ProviderRepresentation represents the ProviderRepresentation schema from the OpenAPI specification
type ProviderRepresentation struct {
	Operationalinfo map[string]interface{} `json:"operationalInfo,omitempty"`
	Order int `json:"order,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	Mapperid string `json:"mapperId,omitempty"`
	Mappername string `json:"mapperName,omitempty"`
	Protocolmapper string `json:"protocolMapper,omitempty"`
	Containerid string `json:"containerId,omitempty"`
	Containername string `json:"containerName,omitempty"`
	Containertype string `json:"containerType,omitempty"`
}

// UserConsentRepresentation represents the UserConsentRepresentation schema from the OpenAPI specification
type UserConsentRepresentation struct {
	Clientid string `json:"clientId,omitempty"`
	Createddate int64 `json:"createdDate,omitempty"`
	Grantedclientscopes []string `json:"grantedClientScopes,omitempty"`
	Lastupdateddate int64 `json:"lastUpdatedDate,omitempty"`
}

// AuthenticatorConfigInfoRepresentation represents the AuthenticatorConfigInfoRepresentation schema from the OpenAPI specification
type AuthenticatorConfigInfoRepresentation struct {
	Properties []ConfigPropertyRepresentation `json:"properties,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Helptext string `json:"helpText,omitempty"`
	Name string `json:"name,omitempty"`
}

// KeysMetadataRepresentation represents the KeysMetadataRepresentation schema from the OpenAPI specification
type KeysMetadataRepresentation struct {
	Active map[string]interface{} `json:"active,omitempty"`
	Keys []GeneratedType `json:"keys,omitempty"`
}

// UserFederationMapperRepresentation represents the UserFederationMapperRepresentation schema from the OpenAPI specification
type UserFederationMapperRepresentation struct {
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Federationmappertype string `json:"federationMapperType,omitempty"`
	Federationproviderdisplayname string `json:"federationProviderDisplayName,omitempty"`
}

// AuthenticationExecutionExportRepresentation represents the AuthenticationExecutionExportRepresentation schema from the OpenAPI specification
type AuthenticationExecutionExportRepresentation struct {
	Authenticatorconfig string `json:"authenticatorConfig,omitempty"`
	Authenticatorflow bool `json:"authenticatorFlow,omitempty"`
	Autheticatorflow bool `json:"autheticatorFlow,omitempty"`
	Flowalias string `json:"flowAlias,omitempty"`
	Priority int `json:"priority,omitempty"`
	Requirement string `json:"requirement,omitempty"`
	Usersetupallowed bool `json:"userSetupAllowed,omitempty"`
	Authenticator string `json:"authenticator,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	Roles []string `json:"roles,omitempty"`
	Verify_caller bool `json:"verify_caller,omitempty"`
}

// ProtocolMapperRepresentation represents the ProtocolMapperRepresentation schema from the OpenAPI specification
type ProtocolMapperRepresentation struct {
	Protocolmapper string `json:"protocolMapper,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Protocol string `json:"protocol,omitempty"`
}

// UserFederationProviderRepresentation represents the UserFederationProviderRepresentation schema from the OpenAPI specification
type UserFederationProviderRepresentation struct {
	Priority int `json:"priority,omitempty"`
	Providername string `json:"providerName,omitempty"`
	Changedsyncperiod int `json:"changedSyncPeriod,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Displayname string `json:"displayName,omitempty"`
	Fullsyncperiod int `json:"fullSyncPeriod,omitempty"`
	Id string `json:"id,omitempty"`
	Lastsync int `json:"lastSync,omitempty"`
}

// AuthenticationExecutionRepresentation represents the AuthenticationExecutionRepresentation schema from the OpenAPI specification
type AuthenticationExecutionRepresentation struct {
	Requirement string `json:"requirement,omitempty"`
	Parentflow string `json:"parentFlow,omitempty"`
	Priority int `json:"priority,omitempty"`
	Authenticator string `json:"authenticator,omitempty"`
	Autheticatorflow bool `json:"autheticatorFlow,omitempty"`
	Flowid string `json:"flowId,omitempty"`
	Id string `json:"id,omitempty"`
	Authenticatorconfig string `json:"authenticatorConfig,omitempty"`
	Authenticatorflow bool `json:"authenticatorFlow,omitempty"`
}

// ClientScopeRepresentation represents the ClientScopeRepresentation schema from the OpenAPI specification
type ClientScopeRepresentation struct {
	Protocol string `json:"protocol,omitempty"`
	Protocolmappers []ProtocolMapperRepresentation `json:"protocolMappers,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
	Description string `json:"description,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
}

// FederatedIdentityRepresentation represents the FederatedIdentityRepresentation schema from the OpenAPI specification
type FederatedIdentityRepresentation struct {
	Identityprovider string `json:"identityProvider,omitempty"`
	Userid string `json:"userId,omitempty"`
	Username string `json:"userName,omitempty"`
}

// KeyStoreConfig represents the KeyStoreConfig schema from the OpenAPI specification
type KeyStoreConfig struct {
	Realmalias string `json:"realmAlias,omitempty"`
	Realmcertificate bool `json:"realmCertificate,omitempty"`
	Storepassword string `json:"storePassword,omitempty"`
	Format string `json:"format,omitempty"`
	Keyalias string `json:"keyAlias,omitempty"`
	Keypassword string `json:"keyPassword,omitempty"`
}

// ManagementPermissionReference represents the ManagementPermissionReference schema from the OpenAPI specification
type ManagementPermissionReference struct {
	Scopepermissions map[string]interface{} `json:"scopePermissions,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Resource string `json:"resource,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	X5t_s256 string `json:"x5t#S256,omitempty"`
}

// MultivaluedHashMap represents the MultivaluedHashMap schema from the OpenAPI specification
type MultivaluedHashMap struct {
	Threshold int `json:"threshold,omitempty"`
	Empty bool `json:"empty,omitempty"`
	Loadfactor float32 `json:"loadFactor,omitempty"`
}

// AddressClaimSet represents the AddressClaimSet schema from the OpenAPI specification
type AddressClaimSet struct {
	Region string `json:"region,omitempty"`
	Street_address string `json:"street_address,omitempty"`
	Country string `json:"country,omitempty"`
	Formatted string `json:"formatted,omitempty"`
	Locality string `json:"locality,omitempty"`
	Postal_code string `json:"postal_code,omitempty"`
}

// SpiInfoRepresentation represents the SpiInfoRepresentation schema from the OpenAPI specification
type SpiInfoRepresentation struct {
	Internal bool `json:"internal,omitempty"`
	Providers map[string]interface{} `json:"providers,omitempty"`
}

// AuthDetailsRepresentation represents the AuthDetailsRepresentation schema from the OpenAPI specification
type AuthDetailsRepresentation struct {
	Ipaddress string `json:"ipAddress,omitempty"`
	Realmid string `json:"realmId,omitempty"`
	Userid string `json:"userId,omitempty"`
	Clientid string `json:"clientId,omitempty"`
}

// ComponentRepresentation represents the ComponentRepresentation schema from the OpenAPI specification
type ComponentRepresentation struct {
	Config MultivaluedHashMap `json:"config,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Parentid string `json:"parentId,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Providertype string `json:"providerType,omitempty"`
	Subtype string `json:"subType,omitempty"`
}

// IdentityProviderMapperRepresentation represents the IdentityProviderMapperRepresentation schema from the OpenAPI specification
type IdentityProviderMapperRepresentation struct {
	Identityprovidermapper string `json:"identityProviderMapper,omitempty"`
	Name string `json:"name,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Id string `json:"id,omitempty"`
	Identityprovideralias string `json:"identityProviderAlias,omitempty"`
}

// ServerInfoRepresentation represents the ServerInfoRepresentation schema from the OpenAPI specification
type ServerInfoRepresentation struct {
	Themes map[string]interface{} `json:"themes,omitempty"`
	Builtinprotocolmappers map[string]interface{} `json:"builtinProtocolMappers,omitempty"`
	Profileinfo ProfileInfoRepresentation `json:"profileInfo,omitempty"`
	Enums map[string]interface{} `json:"enums,omitempty"`
	Identityproviders []map[string]interface{} `json:"identityProviders,omitempty"`
	Clientimporters []map[string]interface{} `json:"clientImporters,omitempty"`
	Passwordpolicies []PasswordPolicyTypeRepresentation `json:"passwordPolicies,omitempty"`
	Providers map[string]interface{} `json:"providers,omitempty"`
	Socialproviders []map[string]interface{} `json:"socialProviders,omitempty"`
	Clientinstallations map[string]interface{} `json:"clientInstallations,omitempty"`
	Componenttypes map[string]interface{} `json:"componentTypes,omitempty"`
	Memoryinfo MemoryInfoRepresentation `json:"memoryInfo,omitempty"`
	Protocolmappertypes map[string]interface{} `json:"protocolMapperTypes,omitempty"`
	Systeminfo SystemInfoRepresentation `json:"systemInfo,omitempty"`
}

// PolicyRepresentation represents the PolicyRepresentation schema from the OpenAPI specification
type PolicyRepresentation struct {
	Scopesdata []ScopeRepresentation `json:"scopesData,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Decisionstrategy string `json:"decisionStrategy,omitempty"`
	Description string `json:"description,omitempty"`
	Logic string `json:"logic,omitempty"`
	Owner string `json:"owner,omitempty"`
	Scopes []string `json:"scopes,omitempty"`
	Id string `json:"id,omitempty"`
	Policies []string `json:"policies,omitempty"`
	Resources []string `json:"resources,omitempty"`
	TypeField string `json:"type,omitempty"`
	Name string `json:"name,omitempty"`
	Resourcesdata []ResourceRepresentation `json:"resourcesData,omitempty"`
}

// ScopeMappingRepresentation represents the ScopeMappingRepresentation schema from the OpenAPI specification
type ScopeMappingRepresentation struct {
	Client string `json:"client,omitempty"`
	Clientscope string `json:"clientScope,omitempty"`
	Roles []string `json:"roles,omitempty"`
	Self string `json:"self,omitempty"`
}

// RealmEventsConfigRepresentation represents the RealmEventsConfigRepresentation schema from the OpenAPI specification
type RealmEventsConfigRepresentation struct {
	Admineventsdetailsenabled bool `json:"adminEventsDetailsEnabled,omitempty"`
	Admineventsenabled bool `json:"adminEventsEnabled,omitempty"`
	Enabledeventtypes []string `json:"enabledEventTypes,omitempty"`
	Eventsenabled bool `json:"eventsEnabled,omitempty"`
	Eventsexpiration int64 `json:"eventsExpiration,omitempty"`
	Eventslisteners []string `json:"eventsListeners,omitempty"`
}

// ClientInitialAccessPresentation represents the ClientInitialAccessPresentation schema from the OpenAPI specification
type ClientInitialAccessPresentation struct {
	Token string `json:"token,omitempty"`
	Count int `json:"count,omitempty"`
	Expiration int `json:"expiration,omitempty"`
	Id string `json:"id,omitempty"`
	Remainingcount int `json:"remainingCount,omitempty"`
	Timestamp int `json:"timestamp,omitempty"`
}

// SystemInfoRepresentation represents the SystemInfoRepresentation schema from the OpenAPI specification
type SystemInfoRepresentation struct {
	Userlocale string `json:"userLocale,omitempty"`
	Username string `json:"userName,omitempty"`
	Version string `json:"version,omitempty"`
	Servertime string `json:"serverTime,omitempty"`
	Fileencoding string `json:"fileEncoding,omitempty"`
	Userdir string `json:"userDir,omitempty"`
	Javavm string `json:"javaVm,omitempty"`
	Osname string `json:"osName,omitempty"`
	Uptimemillis int64 `json:"uptimeMillis,omitempty"`
	Javaruntime string `json:"javaRuntime,omitempty"`
	Javahome string `json:"javaHome,omitempty"`
	Osarchitecture string `json:"osArchitecture,omitempty"`
	Osversion string `json:"osVersion,omitempty"`
	Usertimezone string `json:"userTimezone,omitempty"`
	Javavendor string `json:"javaVendor,omitempty"`
	Javaversion string `json:"javaVersion,omitempty"`
	Javavmversion string `json:"javaVmVersion,omitempty"`
	Uptime string `json:"uptime,omitempty"`
}

// MemoryInfoRepresentation represents the MemoryInfoRepresentation schema from the OpenAPI specification
type MemoryInfoRepresentation struct {
	Total int64 `json:"total,omitempty"`
	Totalformated string `json:"totalFormated,omitempty"`
	Used int64 `json:"used,omitempty"`
	Usedformated string `json:"usedFormated,omitempty"`
	Free int64 `json:"free,omitempty"`
	Freeformated string `json:"freeFormated,omitempty"`
	Freepercentage int64 `json:"freePercentage,omitempty"`
}

// ComponentTypeRepresentation represents the ComponentTypeRepresentation schema from the OpenAPI specification
type ComponentTypeRepresentation struct {
	Metadata map[string]interface{} `json:"metadata,omitempty"`
	Properties []ConfigPropertyRepresentation `json:"properties,omitempty"`
	Helptext string `json:"helpText,omitempty"`
	Id string `json:"id,omitempty"`
}

// ClientRepresentation represents the ClientRepresentation schema from the OpenAPI specification
type ClientRepresentation struct {
	Origin string `json:"origin,omitempty"`
	Name string `json:"name,omitempty"`
	Notbefore int `json:"notBefore,omitempty"`
	Frontchannellogout bool `json:"frontchannelLogout,omitempty"`
	Weborigins []string `json:"webOrigins,omitempty"`
	Access map[string]interface{} `json:"access,omitempty"`
	Protocol string `json:"protocol,omitempty"`
	Baseurl string `json:"baseUrl,omitempty"`
	Surrogateauthrequired bool `json:"surrogateAuthRequired,omitempty"`
	Authorizationsettings ResourceServerRepresentation `json:"authorizationSettings,omitempty"`
	Rooturl string `json:"rootUrl,omitempty"`
	Redirecturis []string `json:"redirectUris,omitempty"`
	Secret string `json:"secret,omitempty"`
	Alwaysdisplayinconsole bool `json:"alwaysDisplayInConsole,omitempty"`
	Defaultroles []string `json:"defaultRoles,omitempty"`
	Registrationaccesstoken string `json:"registrationAccessToken,omitempty"`
	Clientauthenticatortype string `json:"clientAuthenticatorType,omitempty"`
	Publicclient bool `json:"publicClient,omitempty"`
	Description string `json:"description,omitempty"`
	Defaultclientscopes []string `json:"defaultClientScopes,omitempty"`
	Standardflowenabled bool `json:"standardFlowEnabled,omitempty"`
	Implicitflowenabled bool `json:"implicitFlowEnabled,omitempty"`
	Consentrequired bool `json:"consentRequired,omitempty"`
	Clientid string `json:"clientId,omitempty"`
	Serviceaccountsenabled bool `json:"serviceAccountsEnabled,omitempty"`
	Adminurl string `json:"adminUrl,omitempty"`
	Authenticationflowbindingoverrides map[string]interface{} `json:"authenticationFlowBindingOverrides,omitempty"`
	Fullscopeallowed bool `json:"fullScopeAllowed,omitempty"`
	Directaccessgrantsenabled bool `json:"directAccessGrantsEnabled,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Registerednodes map[string]interface{} `json:"registeredNodes,omitempty"`
	Beareronly bool `json:"bearerOnly,omitempty"`
	Authorizationservicesenabled bool `json:"authorizationServicesEnabled,omitempty"`
	Protocolmappers []ProtocolMapperRepresentation `json:"protocolMappers,omitempty"`
	Id string `json:"id,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
	Optionalclientscopes []string `json:"optionalClientScopes,omitempty"`
	Nodereregistrationtimeout int `json:"nodeReRegistrationTimeout,omitempty"`
}

// ConfigPropertyRepresentation represents the ConfigPropertyRepresentation schema from the OpenAPI specification
type ConfigPropertyRepresentation struct {
	Options []string `json:"options,omitempty"`
	Secret bool `json:"secret,omitempty"`
	TypeField string `json:"type,omitempty"`
	Defaultvalue map[string]interface{} `json:"defaultValue,omitempty"`
	Helptext string `json:"helpText,omitempty"`
	Label string `json:"label,omitempty"`
	Name string `json:"name,omitempty"`
}

// TestLdapConnectionRepresentation represents the TestLdapConnectionRepresentation schema from the OpenAPI specification
type TestLdapConnectionRepresentation struct {
	Bindcredential string `json:"bindCredential,omitempty"`
	Binddn string `json:"bindDn,omitempty"`
	Componentid string `json:"componentId,omitempty"`
	Connectiontimeout string `json:"connectionTimeout,omitempty"`
	Connectionurl string `json:"connectionUrl,omitempty"`
	Starttls string `json:"startTls,omitempty"`
	Usetruststorespi string `json:"useTruststoreSpi,omitempty"`
	Action string `json:"action,omitempty"`
}

// IdentityProviderRepresentation represents the IdentityProviderRepresentation schema from the OpenAPI specification
type IdentityProviderRepresentation struct {
	Addreadtokenroleoncreate bool `json:"addReadTokenRoleOnCreate,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
	Displayname string `json:"displayName,omitempty"`
	Firstbrokerloginflowalias string `json:"firstBrokerLoginFlowAlias,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Storetoken bool `json:"storeToken,omitempty"`
	Trustemail bool `json:"trustEmail,omitempty"`
	Linkonly bool `json:"linkOnly,omitempty"`
	Alias string `json:"alias,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Internalid string `json:"internalId,omitempty"`
	Postbrokerloginflowalias string `json:"postBrokerLoginFlowAlias,omitempty"`
}

// AuthenticationExecutionInfoRepresentation represents the AuthenticationExecutionInfoRepresentation schema from the OpenAPI specification
type AuthenticationExecutionInfoRepresentation struct {
	Providerid string `json:"providerId,omitempty"`
	Authenticationconfig string `json:"authenticationConfig,omitempty"`
	Authenticationflow bool `json:"authenticationFlow,omitempty"`
	Configurable bool `json:"configurable,omitempty"`
	Flowid string `json:"flowId,omitempty"`
	Id string `json:"id,omitempty"`
	Index int `json:"index,omitempty"`
	Requirementchoices []string `json:"requirementChoices,omitempty"`
	Displayname string `json:"displayName,omitempty"`
	Requirement string `json:"requirement,omitempty"`
	Alias string `json:"alias,omitempty"`
	Level int `json:"level,omitempty"`
}

// RealmRepresentation represents the RealmRepresentation schema from the OpenAPI specification
type RealmRepresentation struct {
	Otppolicytype string `json:"otpPolicyType,omitempty"`
	Clients []ClientRepresentation `json:"clients,omitempty"`
	Defaultsignaturealgorithm string `json:"defaultSignatureAlgorithm,omitempty"`
	Webauthnpolicypasswordlesssignaturealgorithms []string `json:"webAuthnPolicyPasswordlessSignatureAlgorithms,omitempty"`
	Failurefactor int `json:"failureFactor,omitempty"`
	Eventsexpiration int64 `json:"eventsExpiration,omitempty"`
	Ssosessionidletimeoutrememberme int `json:"ssoSessionIdleTimeoutRememberMe,omitempty"`
	Authenticatorconfig []AuthenticatorConfigRepresentation `json:"authenticatorConfig,omitempty"`
	Webauthnpolicyattestationconveyancepreference string `json:"webAuthnPolicyAttestationConveyancePreference,omitempty"`
	Directgrantflow string `json:"directGrantFlow,omitempty"`
	Webauthnpolicyuserverificationrequirement string `json:"webAuthnPolicyUserVerificationRequirement,omitempty"`
	Displayname string `json:"displayName,omitempty"`
	Registrationflow string `json:"registrationFlow,omitempty"`
	Browsersecurityheaders map[string]interface{} `json:"browserSecurityHeaders,omitempty"`
	Offlinesessionmaxlifespan int `json:"offlineSessionMaxLifespan,omitempty"`
	Verifyemail bool `json:"verifyEmail,omitempty"`
	Webauthnpolicypasswordlessavoidsameauthenticatorregister bool `json:"webAuthnPolicyPasswordlessAvoidSameAuthenticatorRegister,omitempty"`
	Accesstokenlifespanforimplicitflow int `json:"accessTokenLifespanForImplicitFlow,omitempty"`
	Offlinesessionmaxlifespanenabled bool `json:"offlineSessionMaxLifespanEnabled,omitempty"`
	Otppolicyalgorithm string `json:"otpPolicyAlgorithm,omitempty"`
	Editusernameallowed bool `json:"editUsernameAllowed,omitempty"`
	Logintheme string `json:"loginTheme,omitempty"`
	Webauthnpolicyrequireresidentkey string `json:"webAuthnPolicyRequireResidentKey,omitempty"`
	Otpsupportedapplications []string `json:"otpSupportedApplications,omitempty"`
	Requiredactions []RequiredActionProviderRepresentation `json:"requiredActions,omitempty"`
	Refreshtokenmaxreuse int `json:"refreshTokenMaxReuse,omitempty"`
	Internationalizationenabled bool `json:"internationalizationEnabled,omitempty"`
	Clientsessionmaxlifespan int `json:"clientSessionMaxLifespan,omitempty"`
	Clientauthenticationflow string `json:"clientAuthenticationFlow,omitempty"`
	Webauthnpolicypasswordlessrequireresidentkey string `json:"webAuthnPolicyPasswordlessRequireResidentKey,omitempty"`
	Supportedlocales []string `json:"supportedLocales,omitempty"`
	Clientscopes []ClientScopeRepresentation `json:"clientScopes,omitempty"`
	Webauthnpolicypasswordlessrpentityname string `json:"webAuthnPolicyPasswordlessRpEntityName,omitempty"`
	Accesscodelifespan int `json:"accessCodeLifespan,omitempty"`
	Emailtheme string `json:"emailTheme,omitempty"`
	Resetpasswordallowed bool `json:"resetPasswordAllowed,omitempty"`
	Browserflow string `json:"browserFlow,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Offlinesessionidletimeout int `json:"offlineSessionIdleTimeout,omitempty"`
	Permanentlockout bool `json:"permanentLockout,omitempty"`
	Protocolmappers []ProtocolMapperRepresentation `json:"protocolMappers,omitempty"`
	Admineventsdetailsenabled bool `json:"adminEventsDetailsEnabled,omitempty"`
	Webauthnpolicyacceptableaaguids []string `json:"webAuthnPolicyAcceptableAaguids,omitempty"`
	Accesscodelifespanuseraction int `json:"accessCodeLifespanUserAction,omitempty"`
	Maxfailurewaitseconds int `json:"maxFailureWaitSeconds,omitempty"`
	Webauthnpolicypasswordlessuserverificationrequirement string `json:"webAuthnPolicyPasswordlessUserVerificationRequirement,omitempty"`
	Resetcredentialsflow string `json:"resetCredentialsFlow,omitempty"`
	Authenticationflows []AuthenticationFlowRepresentation `json:"authenticationFlows,omitempty"`
	Actiontokengeneratedbyuserlifespan int `json:"actionTokenGeneratedByUserLifespan,omitempty"`
	Otppolicyinitialcounter int `json:"otpPolicyInitialCounter,omitempty"`
	Identityprovidermappers []IdentityProviderMapperRepresentation `json:"identityProviderMappers,omitempty"`
	Ssosessionmaxlifespan int `json:"ssoSessionMaxLifespan,omitempty"`
	Minimumquickloginwaitseconds int `json:"minimumQuickLoginWaitSeconds,omitempty"`
	Duplicateemailsallowed bool `json:"duplicateEmailsAllowed,omitempty"`
	Registrationallowed bool `json:"registrationAllowed,omitempty"`
	Webauthnpolicypasswordlessrpid string `json:"webAuthnPolicyPasswordlessRpId,omitempty"`
	Accounttheme string `json:"accountTheme,omitempty"`
	Webauthnpolicycreatetimeout int `json:"webAuthnPolicyCreateTimeout,omitempty"`
	Webauthnpolicypasswordlesscreatetimeout int `json:"webAuthnPolicyPasswordlessCreateTimeout,omitempty"`
	Registrationemailasusername bool `json:"registrationEmailAsUsername,omitempty"`
	Webauthnpolicypasswordlessacceptableaaguids []string `json:"webAuthnPolicyPasswordlessAcceptableAaguids,omitempty"`
	Sslrequired string `json:"sslRequired,omitempty"`
	Webauthnpolicysignaturealgorithms []string `json:"webAuthnPolicySignatureAlgorithms,omitempty"`
	Dockerauthenticationflow string `json:"dockerAuthenticationFlow,omitempty"`
	Maxdeltatimeseconds int `json:"maxDeltaTimeSeconds,omitempty"`
	Webauthnpolicyrpentityname string `json:"webAuthnPolicyRpEntityName,omitempty"`
	Identityproviders []IdentityProviderRepresentation `json:"identityProviders,omitempty"`
	Revokerefreshtoken bool `json:"revokeRefreshToken,omitempty"`
	Eventslisteners []string `json:"eventsListeners,omitempty"`
	Users []UserRepresentation `json:"users,omitempty"`
	Defaultlocale string `json:"defaultLocale,omitempty"`
	Clientscopemappings map[string]interface{} `json:"clientScopeMappings,omitempty"`
	Userfederationmappers []UserFederationMapperRepresentation `json:"userFederationMappers,omitempty"`
	Webauthnpolicypasswordlessauthenticatorattachment string `json:"webAuthnPolicyPasswordlessAuthenticatorAttachment,omitempty"`
	Admineventsenabled bool `json:"adminEventsEnabled,omitempty"`
	Quicklogincheckmilliseconds int64 `json:"quickLoginCheckMilliSeconds,omitempty"`
	Scopemappings []ScopeMappingRepresentation `json:"scopeMappings,omitempty"`
	Id string `json:"id,omitempty"`
	Roles RolesRepresentation `json:"roles,omitempty"`
	Webauthnpolicyavoidsameauthenticatorregister bool `json:"webAuthnPolicyAvoidSameAuthenticatorRegister,omitempty"`
	Webauthnpolicyauthenticatorattachment string `json:"webAuthnPolicyAuthenticatorAttachment,omitempty"`
	Accesscodelifespanlogin int `json:"accessCodeLifespanLogin,omitempty"`
	Ssosessionidletimeout int `json:"ssoSessionIdleTimeout,omitempty"`
	Enabledeventtypes []string `json:"enabledEventTypes,omitempty"`
	Keycloakversion string `json:"keycloakVersion,omitempty"`
	Otppolicydigits int `json:"otpPolicyDigits,omitempty"`
	Userfederationproviders []UserFederationProviderRepresentation `json:"userFederationProviders,omitempty"`
	Defaultroles []string `json:"defaultRoles,omitempty"`
	Loginwithemailallowed bool `json:"loginWithEmailAllowed,omitempty"`
	Admintheme string `json:"adminTheme,omitempty"`
	Federatedusers []UserRepresentation `json:"federatedUsers,omitempty"`
	Displaynamehtml string `json:"displayNameHtml,omitempty"`
	Defaultoptionalclientscopes []string `json:"defaultOptionalClientScopes,omitempty"`
	Components MultivaluedHashMap `json:"components,omitempty"`
	Groups []GroupRepresentation `json:"groups,omitempty"`
	Otppolicylookaheadwindow int `json:"otpPolicyLookAheadWindow,omitempty"`
	Accesstokenlifespan int `json:"accessTokenLifespan,omitempty"`
	Webauthnpolicyrpid string `json:"webAuthnPolicyRpId,omitempty"`
	Bruteforceprotected bool `json:"bruteForceProtected,omitempty"`
	Clientsessionidletimeout int `json:"clientSessionIdleTimeout,omitempty"`
	Defaultdefaultclientscopes []string `json:"defaultDefaultClientScopes,omitempty"`
	Smtpserver map[string]interface{} `json:"smtpServer,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
	Ssosessionmaxlifespanrememberme int `json:"ssoSessionMaxLifespanRememberMe,omitempty"`
	Realm string `json:"realm,omitempty"`
	Otppolicyperiod int `json:"otpPolicyPeriod,omitempty"`
	Eventsenabled bool `json:"eventsEnabled,omitempty"`
	Usermanagedaccessallowed bool `json:"userManagedAccessAllowed,omitempty"`
	Defaultgroups []string `json:"defaultGroups,omitempty"`
	Actiontokengeneratedbyadminlifespan int `json:"actionTokenGeneratedByAdminLifespan,omitempty"`
	Waitincrementseconds int `json:"waitIncrementSeconds,omitempty"`
	Passwordpolicy string `json:"passwordPolicy,omitempty"`
	Notbefore int `json:"notBefore,omitempty"`
	Webauthnpolicypasswordlessattestationconveyancepreference string `json:"webAuthnPolicyPasswordlessAttestationConveyancePreference,omitempty"`
	Rememberme bool `json:"rememberMe,omitempty"`
}

// RolesRepresentation represents the RolesRepresentation schema from the OpenAPI specification
type RolesRepresentation struct {
	Client map[string]interface{} `json:"client,omitempty"`
	Realm []RoleRepresentation `json:"realm,omitempty"`
}

// AdminEventRepresentation represents the AdminEventRepresentation schema from the OpenAPI specification
type AdminEventRepresentation struct {
	Time int64 `json:"time,omitempty"`
	Authdetails AuthDetailsRepresentation `json:"authDetails,omitempty"`
	ErrorField string `json:"error,omitempty"`
	Operationtype string `json:"operationType,omitempty"`
	Realmid string `json:"realmId,omitempty"`
	Representation string `json:"representation,omitempty"`
	Resourcepath string `json:"resourcePath,omitempty"`
	Resourcetype string `json:"resourceType,omitempty"`
}

// GlobalRequestResult represents the GlobalRequestResult schema from the OpenAPI specification
type GlobalRequestResult struct {
	Failedrequests []string `json:"failedRequests,omitempty"`
	Successrequests []string `json:"successRequests,omitempty"`
}

// ClientInitialAccessCreatePresentation represents the ClientInitialAccessCreatePresentation schema from the OpenAPI specification
type ClientInitialAccessCreatePresentation struct {
	Count int `json:"count,omitempty"`
	Expiration int `json:"expiration,omitempty"`
}

// AuthenticationFlowRepresentation represents the AuthenticationFlowRepresentation schema from the OpenAPI specification
type AuthenticationFlowRepresentation struct {
	Alias string `json:"alias,omitempty"`
	Authenticationexecutions []AuthenticationExecutionExportRepresentation `json:"authenticationExecutions,omitempty"`
	Builtin bool `json:"builtIn,omitempty"`
	Description string `json:"description,omitempty"`
	Id string `json:"id,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Toplevel bool `json:"topLevel,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	Permissions []Permission `json:"permissions,omitempty"`
}

// ClientMappingsRepresentation represents the ClientMappingsRepresentation schema from the OpenAPI specification
type ClientMappingsRepresentation struct {
	Client string `json:"client,omitempty"`
	Id string `json:"id,omitempty"`
	Mappings []RoleRepresentation `json:"mappings,omitempty"`
}

// CredentialRepresentation represents the CredentialRepresentation schema from the OpenAPI specification
type CredentialRepresentation struct {
	Secretdata string `json:"secretData,omitempty"`
	Userlabel string `json:"userLabel,omitempty"`
	Value string `json:"value,omitempty"`
	Createddate int64 `json:"createdDate,omitempty"`
	Credentialdata string `json:"credentialData,omitempty"`
	Priority int `json:"priority,omitempty"`
	Temporary bool `json:"temporary,omitempty"`
	TypeField string `json:"type,omitempty"`
	Id string `json:"id,omitempty"`
}

// CertificateRepresentation represents the CertificateRepresentation schema from the OpenAPI specification
type CertificateRepresentation struct {
	Certificate string `json:"certificate,omitempty"`
	Kid string `json:"kid,omitempty"`
	Privatekey string `json:"privateKey,omitempty"`
	Publickey string `json:"publicKey,omitempty"`
}

// RoleRepresentation represents the RoleRepresentation schema from the OpenAPI specification
type RoleRepresentation struct {
	Containerid string `json:"containerId,omitempty"`
	Description string `json:"description,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
	Clientrole bool `json:"clientRole,omitempty"`
	Composite bool `json:"composite,omitempty"`
	Composites GeneratedType `json:"composites,omitempty"`
}

// GroupRepresentation represents the GroupRepresentation schema from the OpenAPI specification
type GroupRepresentation struct {
	Clientroles map[string]interface{} `json:"clientRoles,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Path string `json:"path,omitempty"`
	Realmroles []string `json:"realmRoles,omitempty"`
	Subgroups []GroupRepresentation `json:"subGroups,omitempty"`
	Access map[string]interface{} `json:"access,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
}

// AccessToken represents the AccessToken schema from the OpenAPI specification
type AccessToken struct {
	Phone_number string `json:"phone_number,omitempty"`
	Authorization GeneratedType `json:"authorization,omitempty"`
	Claims_locales string `json:"claims_locales,omitempty"`
	Email_verified bool `json:"email_verified,omitempty"`
	Exp int64 `json:"exp,omitempty"`
	Email string `json:"email,omitempty"`
	Iat int64 `json:"iat,omitempty"`
	Birthdate string `json:"birthdate,omitempty"`
	Family_name string `json:"family_name,omitempty"`
	Scope string `json:"scope,omitempty"`
	Nonce string `json:"nonce,omitempty"`
	C_hash string `json:"c_hash,omitempty"`
	Realm_access GeneratedType `json:"realm_access,omitempty"`
	Jti string `json:"jti,omitempty"`
	Otherclaims map[string]interface{} `json:"otherClaims,omitempty"`
	Picture string `json:"picture,omitempty"`
	Category string `json:"category,omitempty"`
	Given_name string `json:"given_name,omitempty"`
	Website string `json:"website,omitempty"`
	Updated_at int64 `json:"updated_at,omitempty"`
	Preferred_username string `json:"preferred_username,omitempty"`
	Allowed_origins []string `json:"allowed-origins,omitempty"`
	Cnf GeneratedType `json:"cnf,omitempty"`
	Typ string `json:"typ,omitempty"`
	At_hash string `json:"at_hash,omitempty"`
	Phone_number_verified bool `json:"phone_number_verified,omitempty"`
	Locale string `json:"locale,omitempty"`
	Trusted_certs []string `json:"trusted-certs,omitempty"`
	Address AddressClaimSet `json:"address,omitempty"`
	Session_state string `json:"session_state,omitempty"`
	S_hash string `json:"s_hash,omitempty"`
	Sub string `json:"sub,omitempty"`
	Name string `json:"name,omitempty"`
	Middle_name string `json:"middle_name,omitempty"`
	Profile string `json:"profile,omitempty"`
	Azp string `json:"azp,omitempty"`
	Nickname string `json:"nickname,omitempty"`
	Acr string `json:"acr,omitempty"`
	Gender string `json:"gender,omitempty"`
	Nbf int64 `json:"nbf,omitempty"`
	Auth_time int64 `json:"auth_time,omitempty"`
	Zoneinfo string `json:"zoneinfo,omitempty"`
	Iss string `json:"iss,omitempty"`
}

// MappingsRepresentation represents the MappingsRepresentation schema from the OpenAPI specification
type MappingsRepresentation struct {
	Realmmappings []RoleRepresentation `json:"realmMappings,omitempty"`
	Clientmappings map[string]interface{} `json:"clientMappings,omitempty"`
}

// ProfileInfoRepresentation represents the ProfileInfoRepresentation schema from the OpenAPI specification
type ProfileInfoRepresentation struct {
	Disabledfeatures []string `json:"disabledFeatures,omitempty"`
	Experimentalfeatures []string `json:"experimentalFeatures,omitempty"`
	Name string `json:"name,omitempty"`
	Previewfeatures []string `json:"previewFeatures,omitempty"`
}

// PasswordPolicyTypeRepresentation represents the PasswordPolicyTypeRepresentation schema from the OpenAPI specification
type PasswordPolicyTypeRepresentation struct {
	Displayname string `json:"displayName,omitempty"`
	Id string `json:"id,omitempty"`
	Multiplesupported bool `json:"multipleSupported,omitempty"`
	Configtype string `json:"configType,omitempty"`
	Defaultvalue string `json:"defaultValue,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	Client map[string]interface{} `json:"client,omitempty"`
	Realm []string `json:"realm,omitempty"`
}

// UserRepresentation represents the UserRepresentation schema from the OpenAPI specification
type UserRepresentation struct {
	Lastname string `json:"lastName,omitempty"`
	Self string `json:"self,omitempty"`
	Access map[string]interface{} `json:"access,omitempty"`
	Username string `json:"username,omitempty"`
	Requiredactions []string `json:"requiredActions,omitempty"`
	Credentials []CredentialRepresentation `json:"credentials,omitempty"`
	Realmroles []string `json:"realmRoles,omitempty"`
	Firstname string `json:"firstName,omitempty"`
	Groups []string `json:"groups,omitempty"`
	Serviceaccountclientid string `json:"serviceAccountClientId,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Clientroles map[string]interface{} `json:"clientRoles,omitempty"`
	Notbefore int `json:"notBefore,omitempty"`
	Clientconsents []UserConsentRepresentation `json:"clientConsents,omitempty"`
	Createdtimestamp int64 `json:"createdTimestamp,omitempty"`
	Federatedidentities []FederatedIdentityRepresentation `json:"federatedIdentities,omitempty"`
	Federationlink string `json:"federationLink,omitempty"`
	Emailverified bool `json:"emailVerified,omitempty"`
	Disableablecredentialtypes []string `json:"disableableCredentialTypes,omitempty"`
	Origin string `json:"origin,omitempty"`
	Email string `json:"email,omitempty"`
	Id string `json:"id,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
}

// EventRepresentation represents the EventRepresentation schema from the OpenAPI specification
type EventRepresentation struct {
	Details map[string]interface{} `json:"details,omitempty"`
	ErrorField string `json:"error,omitempty"`
	Realmid string `json:"realmId,omitempty"`
	Ipaddress string `json:"ipAddress,omitempty"`
	TypeField string `json:"type,omitempty"`
	Userid string `json:"userId,omitempty"`
	Sessionid string `json:"sessionId,omitempty"`
	Clientid string `json:"clientId,omitempty"`
	Time int64 `json:"time,omitempty"`
}

// ResourceServerRepresentation represents the ResourceServerRepresentation schema from the OpenAPI specification
type ResourceServerRepresentation struct {
	Policyenforcementmode string `json:"policyEnforcementMode,omitempty"`
	Name string `json:"name,omitempty"`
	Resources []ResourceRepresentation `json:"resources,omitempty"`
	Scopes []ScopeRepresentation `json:"scopes,omitempty"`
	Allowremoteresourcemanagement bool `json:"allowRemoteResourceManagement,omitempty"`
	Policies []PolicyRepresentation `json:"policies,omitempty"`
	Clientid string `json:"clientId,omitempty"`
	Decisionstrategy string `json:"decisionStrategy,omitempty"`
	Id string `json:"id,omitempty"`
}

// ResourceRepresentation represents the ResourceRepresentation schema from the OpenAPI specification
type ResourceRepresentation struct {
	TypeField string `json:"type,omitempty"`
	Displayname string `json:"displayName,omitempty"`
	Icon_uri string `json:"icon_uri,omitempty"`
	Ownermanagedaccess bool `json:"ownerManagedAccess,omitempty"`
	Id string `json:"id,omitempty"`
	Name string `json:"name,omitempty"`
	Uris []string `json:"uris,omitempty"`
	Attributes map[string]interface{} `json:"attributes,omitempty"`
	Scopes []ScopeRepresentation `json:"scopes,omitempty"`
}

// AuthenticatorConfigRepresentation represents the AuthenticatorConfigRepresentation schema from the OpenAPI specification
type AuthenticatorConfigRepresentation struct {
	Id string `json:"id,omitempty"`
	Alias string `json:"alias,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
}

// RequiredActionProviderRepresentation represents the RequiredActionProviderRepresentation schema from the OpenAPI specification
type RequiredActionProviderRepresentation struct {
	Defaultaction bool `json:"defaultAction,omitempty"`
	Enabled bool `json:"enabled,omitempty"`
	Name string `json:"name,omitempty"`
	Priority int `json:"priority,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Alias string `json:"alias,omitempty"`
	Config map[string]interface{} `json:"config,omitempty"`
}

// Permission represents the Permission schema from the OpenAPI specification
type Permission struct {
	Rsname string `json:"rsname,omitempty"`
	Scopes []string `json:"scopes,omitempty"`
	Claims map[string]interface{} `json:"claims,omitempty"`
	Rsid string `json:"rsid,omitempty"`
}

// GeneratedType represents the GeneratedType schema from the OpenAPI specification
type GeneratedType struct {
	Status string `json:"status,omitempty"`
	TypeField string `json:"type,omitempty"`
	Algorithm string `json:"algorithm,omitempty"`
	Certificate string `json:"certificate,omitempty"`
	Kid string `json:"kid,omitempty"`
	Providerid string `json:"providerId,omitempty"`
	Providerpriority int64 `json:"providerPriority,omitempty"`
	Publickey string `json:"publicKey,omitempty"`
}
