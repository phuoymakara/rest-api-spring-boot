package com.springapp.spring_api.response;

//import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ApiGlobalErrorResponse {
    /**
     * A developer friendly plain English description of why the HTTP error response was returned from the API.
     */
    @JsonProperty(value = "developerMessage")
    private String developerMessage;

    /**
     * The HTTP status code of the response.
     */
    @JsonProperty(value = "httpCode")
    private String httpStatusCode;

    /**
     * A user friendly plain English description of why the HTTP error response was returned from the API that can be
     * presented to end users.
     */
    @JsonProperty(value = "message")
    private String defaultUserMessage;

    /**
     * A code that can be used for globalisation support by client applications of the API.
     */
    @JsonProperty(value = "code")
    private String userMessageGlobalisationCode;

    /**
     * A list of zero or more of the actual reasons for the HTTP error should they be needed. Typically used to express
     * data validation errors with parameters passed to API.
     */

    private List<ApiParameterError> errors = new ArrayList<>();

    protected ApiGlobalErrorResponse() {
        //
    }

    public ApiGlobalErrorResponse(final List<ApiParameterError> errors) {
        this.errors = errors;
    }

    public static ApiGlobalErrorResponse unAuthenticated() {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("401");
        globalErrorResponse.setDeveloperMessage("Invalid authentication details were passed in api request.");
        globalErrorResponse.setUserMessageGlobalisationCode("error.msg.not.authenticated");
        globalErrorResponse.setDefaultUserMessage("Unauthenticated. Please login.");

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse invalidTenantIdentifier() {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("401");
        globalErrorResponse.setDeveloperMessage("Invalid tenant details were passed in api request.");
        globalErrorResponse.setUserMessageGlobalisationCode("error.msg.invalid.tenant.identifier");
        globalErrorResponse.setDefaultUserMessage("Invalide tenant identifier provided with request.");

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse unAuthorized(final String defaultUserMessage) {
        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("403");
        globalErrorResponse.setDeveloperMessage(
                "The user associated with credentials passed on this request does not have sufficient privileges to perform this action.");
        globalErrorResponse.setUserMessageGlobalisationCode("error.msg.not.authorized");
        globalErrorResponse.setDefaultUserMessage("Insufficient privileges to perform this action.");

        /*final List<ApiParameterError> errors = new ArrayList<>();
        errors.add(ApiParameterError.generalError("error.msg.not.authorized", defaultUserMessage));
        globalErrorResponse.setErrors(errors);*/

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse domainRuleViolation(final String globalisationMessageCode, final String defaultUserMessage,
                                                             final Object... defaultUserMessageArgs) {
        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("403");
        globalErrorResponse.setDeveloperMessage("Request was understood but caused a domain rule violation.");
        globalErrorResponse.setUserMessageGlobalisationCode("validation.msg.domain.rule.violation");
        globalErrorResponse.setDefaultUserMessage("Errors contain reason for domain rule violation.");

        final List<ApiParameterError> errors = new ArrayList<>();
        errors.add(ApiParameterError.generalError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs));
        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse notFound(final String globalisationMessageCode, final String defaultUserMessage,
                                                  final Object... defaultUserMessageArgs) {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("404");
        globalErrorResponse.setDeveloperMessage("The requested resource is not available.");
        globalErrorResponse.setUserMessageGlobalisationCode("error.msg.resource.not.found");
        globalErrorResponse.setDefaultUserMessage("The requested resource is not available.");

        final List<ApiParameterError> errors = new ArrayList<>();
        errors.add(ApiParameterError.resourceIdentifierNotFound(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs));
        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse dataIntegrityError(final String globalisationMessageCode, final String defaultUserMessage,
                                                            final String parameterName, final Object... defaultUserMessageArgs) {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("403");
        globalErrorResponse.setDeveloperMessage("The request caused a data integrity issue to be fired by the database.");
        globalErrorResponse.setUserMessageGlobalisationCode(globalisationMessageCode);
        globalErrorResponse.setDefaultUserMessage(defaultUserMessage);

        final List<ApiParameterError> errors = new ArrayList<>();
        errors.add(ApiParameterError.parameterError(globalisationMessageCode, defaultUserMessage, parameterName, defaultUserMessageArgs));
        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse badClientRequest(final String globalisationMessageCode, final String defaultUserMessage,
                                                          final List<ApiParameterError> errors) {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("400");
        globalErrorResponse
                .setDeveloperMessage("The request was invalid. This typically will happen due to validation errors which are provided.");
        globalErrorResponse.setUserMessageGlobalisationCode(globalisationMessageCode);
        globalErrorResponse.setDefaultUserMessage(defaultUserMessage);

        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse serverSideError(final String globalisationMessageCode, final String defaultUserMessage,
                                                         final Object... defaultUserMessageArgs) {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("500");
        globalErrorResponse.setDeveloperMessage("An unexpected error occured on the platform server.");
        globalErrorResponse.setUserMessageGlobalisationCode(globalisationMessageCode);
        globalErrorResponse.setDefaultUserMessage(defaultUserMessage);

//        final List<ApiParameterError> errors = new ArrayList<>();
//        errors.add(ApiParameterError.generalError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs));
//        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }

    public static ApiGlobalErrorResponse serviceUnavailable(final String globalisationMessageCode, final String defaultUserMessage,
                                                            final Object... defaultUserMessageArgs) {

        final ApiGlobalErrorResponse globalErrorResponse = new ApiGlobalErrorResponse();
        globalErrorResponse.setHttpStatusCode("503");
        globalErrorResponse.setDeveloperMessage("The server is currently unable to handle the request , please try after some time.");
        globalErrorResponse.setUserMessageGlobalisationCode("error.msg.platform.service.unavailable");
        globalErrorResponse.setDefaultUserMessage("The server is currently unable to handle the request , please try after some time.");

        final List<ApiParameterError> errors = new ArrayList<>();
        errors.add(ApiParameterError.generalError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs));
        globalErrorResponse.setErrors(errors);

        return globalErrorResponse;
    }
}
