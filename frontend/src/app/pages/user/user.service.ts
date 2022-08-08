import {BaseCrudService} from "../@core/base-crud/base-crud.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class UserService implements BaseCrudService {
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
    return undefined;
  }

  update(rq: any): Observable<any> | undefined {
    return undefined;
  }


}
