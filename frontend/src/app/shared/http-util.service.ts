import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {TokenStorage} from './service/token-storage';
import {environment} from "../../environments/environment";
import {ApiContext} from "./api-context";

@Injectable({
  providedIn: 'root'
})
export class HttpUtilService extends ApiContext {
  rootContext: string  = environment.host;
  constructor(public http: HttpClient,
              private tokenStorage: TokenStorage) {
    super();
  }

  callAPI(url: string, data: any, responseType?: any): Observable<any> {
    url = this.rootContext + url;
    if (!responseType) {
      responseType = 'json';
    }
    data.responseType = responseType;
    return this.callBase(url, data);
  }

  public callBase(url: string, data: any): Observable<any> {
    let method = 'POST';
    let responseType: any = 'json';
    if (data.method) {
      method = data.method;
      delete data.method;
    }
    if (data.responseType) {
      responseType = data.responseType;
      delete data.responseType;
    }
    let headers = new HttpHeaders();
    if (data && data.authorizationParams) {
      headers = headers.append('Authorization', 'Bearer ' + data.authorizationParams);// = new HttpHeaders({Authorization: 'Bearer ' + data.authorizationParams});
    } else if (this.tokenStorage.getTokenStr()) {
      headers = headers.append('Authorization', 'Bearer ' + this.tokenStorage.getTokenStr());
      // headers = new HttpHeaders({Authorization: 'Bearer ' + this.tokenStorage.getTokenStr()});
    }
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');
    const requestParam = Object.assign({}, data);
    let params = {};
    let body = {};
    if (method === 'GET') {
      params = requestParam;
    } else {
      body = requestParam;
    }
    return this.http.request(method, url, {
      body,
      headers,
      params,
      responseType
    }).pipe(catchError(this.handleError)
    );
  }

  public handleError(error: any) {
    if (error.error) {
      error = error.error;
    }
    return throwError(error);
  }

}
