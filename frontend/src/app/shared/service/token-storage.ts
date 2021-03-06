import {Injectable} from "@angular/core";
import {forkJoin, Observable, of} from "rxjs";
import {CredentialModel} from "../../pages/@core/credential.model";

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

  public getUserProfile(): CredentialModel | undefined {
    const userInfo: any = localStorage.getItem(StorageKey.USER_PROFILE);
    try {
      const item = JSON.parse(userInfo);
      return item;
    } catch (e) {
      return undefined;
    }
  }
  public removeItem(key: string): Observable<void> {
    return of(localStorage.removeItem(key));
  }

  public clear(): Observable<any> {
    const deleteKeys = [StorageKey.ACCESS_TOKEN,
      StorageKey.USER_PROFILE, StorageKey.USER_ROLES,
      StorageKey.MENU_LIST];
    const taskRemove$: any[] = [];
    deleteKeys.forEach(key => {
      taskRemove$.push(this.removeItem(key));
    });
    return forkJoin(taskRemove$);
  }
}
