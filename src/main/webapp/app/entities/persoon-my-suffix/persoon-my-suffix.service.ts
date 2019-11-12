import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

type EntityResponseType = HttpResponse<IPersoonMySuffix>;
type EntityArrayResponseType = HttpResponse<IPersoonMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class PersoonMySuffixService {
  public resourceUrl = SERVER_API_URL + 'api/persoons';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/persoons';

  constructor(protected http: HttpClient) {}

  create(persoon: IPersoonMySuffix): Observable<EntityResponseType> {
    return this.http.post<IPersoonMySuffix>(this.resourceUrl, persoon, { observe: 'response' });
  }

  update(persoon: IPersoonMySuffix): Observable<EntityResponseType> {
    return this.http.put<IPersoonMySuffix>(this.resourceUrl, persoon, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersoonMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersoonMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersoonMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
