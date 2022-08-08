import {Component, OnInit} from "@angular/core";
import {BaseCrudComponent} from "../../@core/base-crud/base-crud.component";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-product-list',
  styleUrls: [],
  templateUrl: './product-list.component.html'
})
export class ProductListComponent extends BaseCrudComponent implements OnInit {
  keyword: string = "";
  constructor(private service: ProductService) {
    super(service);
  }

  getFilterData(): any {
    return {keyword: this.keyword};
  }

  bindList(data: any): any {
    this.rows = data.content;
  }

  ngOnInit(): void {
    this.search();
  }
}
