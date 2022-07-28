import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";

export enum StorageKey {
  ACCESS_TOKEN = 'accessToken',
  USER_ROLES = 'userRoles',
  USER_PROFILE = 'userProfile',
  MENU_LIST = 'menuList',
  ROLE_LIST = 'roleList',
}

@Injectable({
  providedIn: 'root'
})
export class TokenStorage {
  public getAccessToken(): Observable<string> {
    const token: any = localStorage.getItem(StorageKey.ACCESS_TOKEN);
    return of(token);
  }
}
