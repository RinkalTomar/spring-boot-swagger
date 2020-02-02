package com.java;


import com.java.beans.UserBean;
import com.java.beans.request.UserAcountResetOrActivateRequest;
import com.java.beans.response.AbstractResponse;
import com.java.beans.response.AbstractResponseBean;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Api(value = "User", description = "Users API", tags = {"Users"})
public interface UserApi {

    @ApiOperation(value = "Add/Update a new User in the Login Applicaiton", nickname = "AddOrEditUser", notes = "Returns HTTP 201 if user is successfully created",
            response = AbstractResponse.class,
            tags = "Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK."),
            @ApiResponse(code = 201, message = "The request has been fulfilled, and a new User is created .",
                    response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response =
                                                                                                              AbstractResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 409, message = "Duplicate Found. Resource already exists."),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @PostMapping(value = "/users",
            produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<AbstractResponse> addNewUser(@ApiParam(value = "", required = true) @Valid @RequestBody UserBean body)
            throws Exception;


    @ApiOperation(value = "Return List of all users", nickname = "getUsers",
            notes = "Returns HTTP 404 if the " + "Users are not found.", response = UserBean.class,
            responseContainer = "List", tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Request is OK.", response = AbstractResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The Users might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.",
                    response = AbstractResponse.class)})
    @GetMapping(value = "/users", produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getAllUsers(@RequestParam(required = false) Map<String, String> searchParams) throws Exception;


    @ApiOperation(value = "Get the User with the given id", nickname = "getUser",
            notes = "Returns HTTP 200 if the User is successfully activated. Returns HTTP 409 if already Activated",
            response = Void.class,
            tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. User is De-Activated Successfully", response = AbstractResponse
                    .class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The contract might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 406, message = "Not Acceptable. The Request is not Acceptable. Invalid request Format", response = AbstractResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered.", response = AbstractResponse.class),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @GetMapping(value = "/users/{id}",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> getUserDetails(
            @ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;

    @ApiOperation(value = "Delete the User with the given id", nickname = "deleteUser",
            notes = "Returns HTTP 200 if the User is successfully deleted.",
            response = Void.class,
            tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. User is Suspended Successfully", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered."),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @PutMapping(value = "/users/delete",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> deleteAllUser(@RequestBody List<Long> id)
            throws Exception;


    @ApiOperation(value = "Delete the User with the given id", nickname = "deleteUser",
            notes = "Returns HTTP 200 if the User is successfully deleted.",
            response = Void.class,
            tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. User is Suspended Successfully", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered."),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @DeleteMapping(value = "/users/delete/{id}",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> deleteUser(
            @ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id)
            throws Exception;

    @ApiOperation(value = "DeActiveUser the User with the given id", nickname = "DeActiveUser",
            notes = "Returns HTTP 200 if the User is successfully deActiveUser.",
            response = Void.class,
            tags = {"Users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. User is Suspended Successfully", response = AbstractResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. The request cannot be fulfilled due to bad syntax."),
            @ApiResponse(code = 401, message = "Unauthorized. Authentication failed or not provided", response = AbstractResponse.class),
            @ApiResponse(code = 403, message = "Forbidden. The user might not have the necessary permissions for a resource."),
            @ApiResponse(code = 404, message = "Not Found. The Object is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error. An unexpected condition was encountered."),
            @ApiResponse(code = 501, message = "Method not implemented. The server either does not recognize the request method, or it lacks the ability to fulfill the request."),
            @ApiResponse(code = 502, message = "Bad Gateway. The server was acting as a gateway or proxy and received an invalid response from the upstream server"),
            @ApiResponse(code = 503, message = "Service Unavailable. The server is currently unavailable (overloaded or down)."),
            @ApiResponse(code = 504, message = "Gateway Timeout. The server is acting as a gateway or proxy and did not receive a timely response from the upstream server")})
    @PutMapping(value = "/users/deactiveuser",
            produces = {"application/json"})
    ResponseEntity<AbstractResponseBean> deActiveUser(@RequestBody List<Long> id)
            throws Exception;
}
