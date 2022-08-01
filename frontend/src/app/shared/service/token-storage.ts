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

  public getTokenStr(): string {
    return localStorage.getItem(StorageKey.ACCESS_TOKEN) as string;
  }

  public setAccessToken(token: string): TokenStorage {
    localStorage.setItem(StorageKey.ACCESS_TOKEN, token);
    return this;
  }

  public setUserProfile(profile: any): any {
    if (profile != null) {
      delete profile.token;
      localStorage.setItem(StorageKey.USER_PROFILE, JSON.stringify(profile));
    }
    return this;
  }
}
