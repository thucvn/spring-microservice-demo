import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit {
  curLink: string = '';
  constructor(private router: Router) {

  }

  ngOnInit(): void {
    this.curLink = this.router.url;
  }

  route(e: any, link: string) {
    e.preventDefault();
    console.log(link);
    this.curLink = link;
    this.router.navigate([link]);
  }


}
