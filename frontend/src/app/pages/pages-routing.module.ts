import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PagesComponent} from "./pages.component";
import {HomeComponent} from "./@core/home/home.component";
const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [{
    path: '',
    component: HomeComponent
  }]
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
