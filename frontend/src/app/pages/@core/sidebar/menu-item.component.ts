import {Component, Input} from "@angular/core";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  template: '<li class="nav-item nav-menu"> ' +
    '        <a [class]="classActive" href="#" (click)="route($event)"> ' +
    '          <i [class]="icon"></i> ' +
    '          {{name}}' +
    '        </a>' +
    '      </li>',
  selector: 'app-menu-item',
  styleUrls: ['./sidebar.component.scss']
})
export class MenuItemComponent {
  @Input() name: string = '';
  @Input() link: string = '';
  @Input() icon: string = '';
  classActive: string = 'nav-link';
  cur: string = '';
  constructor(private router: Router) {
    router.events.subscribe(val => {
      if (val instanceof NavigationEnd) {
        this.cur = val.url;
        this.updateActive();
      }
    });
  }

  route(e: any) {
    e.preventDefault();
    this.router.navigate([this.link]);
  }

  updateActive() {
    if (this.link === this.cur) this.classActive = 'nav-link active';
    else this.classActive = 'nav-link';
  }
}
