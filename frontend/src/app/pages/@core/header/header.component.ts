import {Component, OnInit} from "@angular/core";
import {TokenStorage} from "../../../shared/service/token-storage";
import {CredentialModel} from "../credential.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [],
})
export class HeaderComponent implements OnInit{
  username: any;
  constructor(private tokenStorage: TokenStorage, private router: Router) {
  }

  ngOnInit(): void {
    let user: CredentialModel = this.tokenStorage.getUserProfile() as CredentialModel;
    this.username = user.name;
  }

  logout() {
    this.tokenStorage.clear().subscribe(() => {
      this.router.navigate(["/auth"]);
    });
  }
}
