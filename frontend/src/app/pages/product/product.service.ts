import {BaseCrudService} from "../@core/base-crud/base-crud.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpUtilService} from "../../shared/http-util.service";

@Injectable()
export class ProductService implements BaseCrudService {
  private readonly API_URL = this.httpUtilService.api.product;
  constructor(private httpUtilService: HttpUtilService) {
  }

  create(rq: any): Observable<any> | undefined {
    return undefined;
  }

  deleteById(rq: any): Observable<any> | undefined {
    return undefined;
  }

  findById(rq: any, obj?: any): Observable<any> | undefined {
    return undefined;
  }

  getList(rq: any, apiName?: any): Observable<any> | undefined {
    return this.httpUtilService.callAPI(this.API_URL + '/product', {...rq, method: 'GET'});
  }

  update(rq: any): Observable<any> | undefined {
    return undefined;
  }


}
