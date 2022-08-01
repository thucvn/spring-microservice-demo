import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth.service";
import {ToastrService} from "ngx-toastr";
import {TokenStorage} from "../../shared/service/token-storage";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
  isLoading: boolean = false;
  constructor(private fb: FormBuilder,
              private service: AuthService,
              private router: Router,
              private tokenStorage: TokenStorage,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.maxLength(100)]],
    });
  }

  login() {
    if (this.isLoading) return;
    this.isLoading = true;
    const rq = this.loginForm.getRawValue();
    this.service.login(rq).subscribe(res => {
      if (res.token) {
        this.tokenStorage.setAccessToken(res.token);
        this.tokenStorage.setUserProfile(res);
        setTimeout(() => {
          this.router.navigate(['/pages']);
        }, 100);
      }
    }, error => {
      this.toastrService.error(error.message);
    }).add(() => {
      this.isLoading = false;
    });
  }
}
