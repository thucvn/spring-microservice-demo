import {NgModule} from "@angular/core";
import {SharedModule} from "../shared/shared.module";
import {LoginComponent} from "./login/login.component";
import {AuthComponent} from "./auth.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    LoginComponent,
    AuthComponent
  ],
    imports: [
        SharedModule,
        AuthRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
    ],
  providers: [
  ]
})
export class AuthModule {
}
