import {RouterModule, Routes} from "@angular/router";
import {ProductComponent} from "./product.component";
import {ProductListComponent} from "./list/product-list.component";
import {NgModule} from "@angular/core";

const routes: Routes = [{
  path: '',
  component: ProductComponent,
  children: [
    {
      path: '',
      component: ProductListComponent,
    }
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {}
