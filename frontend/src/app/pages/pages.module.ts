import {NgModule} from "@angular/core";
import {PagesComponent} from "./pages.component";
import {PagesRoutingModule} from "./pages-routing.module";
import {HeaderComponent} from "./@core/header/header.component";
import {SidebarComponent} from "./@core/sidebar/sidebar.component";
import {FooterComponent} from "./@core/footer/footer.component";
import {HomeComponent} from "./@core/home/home.component";
import {MenuItemComponent} from "./@core/sidebar/menu-item.component";

@NgModule({
  declarations: [
    PagesComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    HomeComponent,
    MenuItemComponent
  ],
  providers: [
  ],
  imports: [
    PagesRoutingModule,
  ],
  exports: [MenuItemComponent]
})
export class PagesModule {

}
