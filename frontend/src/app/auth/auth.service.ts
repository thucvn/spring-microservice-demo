import {Injectable} from "@angular/core";
import {map, Observable} from "rxjs";
import {TokenStorage} from "../shared/service/token-storage";

@Injectable()
export class AuthService {

  constructor(private tokenStorage: TokenStorage) {
  }

  public isAuthorized(): Observable<boolean> {
    return this.tokenStorage.getAccessToken().pipe(map(token => !!token));
  }

}
