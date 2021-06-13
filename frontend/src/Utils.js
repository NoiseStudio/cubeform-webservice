
export const getErrorFromResponseBody = (responseBody) => {

    if(typeof(responseBody) === "string"){
        return responseBody;
    }

    let error = responseBody.error;

    if(error == null || error === "")
        error = responseBody.message;

    if(error == null || error === "")
        error = "Unknown error";

    console.log("err:", error);
    return  error;
}