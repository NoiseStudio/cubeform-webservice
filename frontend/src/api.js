import Store from "@/storage/Store";

const staticHeaders = {
    "Content-Type": "application/json"
}
const tokenType = "Bearer"

export class ApiManager {

    _host;
    _debugInfo = false;
    _headers = {}
    _autoLogout;

    constructor(host, debugInfo = false, autoLogout = true) {
        this._debugInfo = debugInfo;
        this._headers = Object.assign({}, staticHeaders);
        this._host = host;
        this._autoLogout = autoLogout;
        if(this._host.endsWith("/"))
            this._host = this._host.substr(0, this._host.length - 1);

        if(Store.state.User.isLogged)
            this._headers['Authorization'] = `${tokenType} ${Store.state.User.token}`;
        else
            this._headers['Authorization'] = undefined;

        console.log(tokenType);
        Store.subscribe((mutation) => {
            if(mutation.type === "User/setUserToken") {
                this._headers['Authorization'] = `${tokenType} ${mutation.payload}`;
                console.log("+++", mutation.payload);
            }
        });
    }

    set autoLogout(autoLogout){
        this._autoLogout = autoLogout;
    }

    get autoLogout(){
        return this._autoLogout;
    }

    set debugInfo(debugInfo) {
        this._debugInfo = debugInfo;
    }

    get debugInfo(){
        return this._debugInfo;
    }

    setHeader(header, value){
        if(header === 'Authorization')
            return;

        this._headers[header] = value;
    }

    clearHeader(header) {
        if(header === 'Authorization')
            return;

        delete this._headers[header];
    }

    getHeaders(){
        return Object.assign({}, this._headers);
    }

    _handleRequest(apiPath, method, body) {
        let promise = fetch(this._host + apiPath, {
            method: method,
            headers: this._headers,
            body: (typeof(body) === "object") ? JSON.stringify(body) : undefined,
            mode: "cors"
        })
        .then(response => {
            if(this._autoLogout && response.status === 401) {
                Store.dispatch("User/logout");
            }

            return response.json()
                .then(json => {
                    response.jsonBody = json;
                    return response;
                });
        });

        if(this._debugInfo === true){
            promise.then(response => {
                console.log("", response);
                return response;
            });
        }

        return promise
    }

    defaultRequestFailCatch(err){
        console.error("Server not responding");
        console.error(err);
    }

    get(apiPath) {
        return this._handleRequest(apiPath, "GET", undefined);
    }
    post(apiPath, body) {
        return this._handleRequest(apiPath, "POST", body);
    }
    put(apiPath, body) {
        return this._handleRequest(apiPath, "PUT", body);
    }

// ================================================= requests

    register(username, password, email){
        return this.post("/api/auth/register", {
            username: username,
            password: password,
            email: email
        });
    }

    login(username, password) {
        return this.post("/api/auth/login", {
            username: username,
            password: password
        });
    }

    getMe() {
        return this.get("/api/users/@me");
    }

    getNews() {
        return this.get("/api/news");
    }

}

export const api = new ApiManager("http://localhost:8080/");