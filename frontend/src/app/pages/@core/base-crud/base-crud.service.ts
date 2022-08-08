import {Observable} from 'rxjs';

export interface BaseCrudService {
  getList(rq: any, apiName?: any): Observable<any> | undefined;

  findById(rq: any, obj?: any): Observable<any> | undefined;

  create(rq: any): Observable<any> | undefined;

  update(rq: any): Observable<any> | undefined;

  deleteById(rq: any): Observable<any> | undefined;
}
