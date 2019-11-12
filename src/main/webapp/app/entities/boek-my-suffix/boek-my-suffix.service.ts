import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBoekMySuffix } from 'app/shared/model/boek-my-suffix.model';

type EntityResponseType = HttpResponse<IBoekMySuffix>;
type EntityArrayResponseType = HttpResponse<IBoekMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class BoekMySuffixService {
  public resourceUrl = SERVER_API_URL + 'api/boeks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/boeks';

  constructor(protected http: HttpClient) {}

  create(boek: IBoekMySuffix): Observable<EntityResponseType> {
    return this.http.post<IBoekMySuffix>(this.resourceUrl, boek, { observe: 'response' });
  }

  update(boek: IBoekMySuffix): Observable<EntityResponseType> {
    return this.http.put<IBoekMySuffix>(this.resourceUrl, boek, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBoekMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBoekMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBoekMySuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
