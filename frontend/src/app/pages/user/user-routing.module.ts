import {RouterModule, Routes} from "@angular/router";
import {UserComponent} from "./user.component";
import {UserListComponent} from "./list/user-list.component";
import {NgModule} from "@angular/core";

const routes: Routes = [{
  path: '',
  component: UserComponent,
  children: [
    {
      path: '',
      component: UserListComponent,
    }
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {}
