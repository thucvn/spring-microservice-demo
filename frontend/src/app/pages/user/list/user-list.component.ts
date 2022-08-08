import {Component} from "@angular/core";
import {BaseCrudComponent} from "../../@core/base-crud/base-crud.component";
import {UserService} from "../user.service";

@Component({
  selector: 'app-user-list',
  styleUrls: [],
  templateUrl: './user-list.component.html'
})
export class UserListComponent extends BaseCrudComponent{
  constructor(private service: UserService) {
    super(service);
  }

  getFilterData(): any {
  }

  bindList(data: any): any {
  }
}
