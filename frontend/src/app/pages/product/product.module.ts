import {NgModule} from "@angular/core";
import {ProductComponent} from "./product.component";
import {ProductService} from "./product.service";
import {ProductListComponent} from "./list/product-list.component";
import {ProductRoutingModule} from "./product-routing.module";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [ProductRoutingModule, NgbPaginationModule, CommonModule, FormsModule],
  entryComponents: [ProductComponent],
  declarations:[ProductComponent, ProductListComponent],
  providers: [ProductService]
})
export class ProductModule {

}
