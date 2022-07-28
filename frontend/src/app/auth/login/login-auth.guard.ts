import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../auth.service';
import {map} from 'rxjs/operators';

@Injectable()
export class LoginAuthGuard implements CanActivate {

  constructor(private router: Router,
              private authService: AuthService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.authService.isAuthorized().pipe(
      map(value => {
        if (!value) {
          return true;
        } else {
          this.router.navigate(['/pages']);
          return false;
        }
      })
    );
  }
}
