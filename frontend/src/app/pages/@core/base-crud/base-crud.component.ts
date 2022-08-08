import {BaseCrudService} from "./base-crud.service";
import {TokenStorage} from "../../../shared/service/token-storage";
import {ToastrService} from "ngx-toastr";
import {Directive} from "@angular/core";
@Directive()
export abstract class BaseCrudComponent {
  page: number = 1;
  pageSize: number = 10;
  totalElements: number = 0;
  isLoading: boolean = false;
  rows: Array<any> = [];
  protected constructor(private httpService: BaseCrudService,
                        public storage?: TokenStorage,
                        public toastrService?: ToastrService) {
  }

  search() {
    if (this.isLoading) return;
    let rq = this.getFilterData();
    rq.page = this.page - 1;
    rq.pageSize = this.pageSize;
    this.isLoading = true;
    this.httpService.getList(rq)?.subscribe(res => {
      this.bindList(res);
      this.totalElements = res.totalElements;
    }).add(() => {
      this.isLoading = false;
    })
  }

  abstract getFilterData(): any;

  abstract bindList(data: any): any;

}
