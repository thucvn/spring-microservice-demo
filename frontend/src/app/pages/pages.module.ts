import {NgModule} from "@angular/core";
import {PagesComponent} from "./pages.component";
import {PagesRoutingModule} from "./pages-routing.module";
import {HeaderComponent} from "./@core/header/header.component";

@NgModule({
  declarations: [
    PagesComponent,
    HeaderComponent
  ],
  providers: [
  ],
  imports: [
    PagesRoutingModule,
  ]
})
export class PagesModule {

}
