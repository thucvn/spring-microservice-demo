import {Injectable} from "@angular/core";
import {map, Observable} from "rxjs";
import {TokenStorage} from "../shared/service/token-storage";
import {HttpUtilService} from "../shared/http-util.service";

@Injectable()
export class AuthService {
  private readonly API_URL = this.httpUtilService.api.user;
  constructor(private tokenStorage: TokenStorage, private httpUtilService: HttpUtilService) {
  }

  public isAuthorized(): Observable<boolean> {
    return this.tokenStorage.getAccessToken().pipe(map(token => !!token));
  }

  login(credential: any): Observable<any> {
    return this.httpUtilService.callAPI(this.API_URL + '/auth/login', credential);
  }

  test() : Observable<any> {
    return this.httpUtilService.callAPI(this.API_URL + '/user/abc', {method: 'GET'})
  }
}
