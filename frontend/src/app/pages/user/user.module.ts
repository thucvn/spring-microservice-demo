import {NgModule} from "@angular/core";
import {UserComponent} from "./user.component";
import {UserService} from "./user.service";
import {UserListComponent} from "./list/user-list.component";
import {UserRoutingModule} from "./user-routing.module";

@NgModule({
  imports: [UserRoutingModule],
  entryComponents: [UserComponent],
  declarations:[UserComponent, UserListComponent],
  providers: [UserService]
})
export class UserModule {

}
