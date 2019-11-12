import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BoekMySuffix } from 'app/shared/model/boek-my-suffix.model';
import { BoekMySuffixService } from './boek-my-suffix.service';
import { BoekMySuffixComponent } from './boek-my-suffix.component';
import { BoekMySuffixDetailComponent } from './boek-my-suffix-detail.component';
import { BoekMySuffixUpdateComponent } from './boek-my-suffix-update.component';
import { BoekMySuffixDeletePopupComponent } from './boek-my-suffix-delete-dialog.component';
import { IBoekMySuffix } from 'app/shared/model/boek-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class BoekMySuffixResolve implements Resolve<IBoekMySuffix> {
  constructor(private service: BoekMySuffixService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBoekMySuffix> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((boek: HttpResponse<BoekMySuffix>) => boek.body));
    }
    return of(new BoekMySuffix());
  }
}

export const boekRoute: Routes = [
  {
    path: '',
    component: BoekMySuffixComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Boeks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BoekMySuffixDetailComponent,
    resolve: {
      boek: BoekMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Boeks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BoekMySuffixUpdateComponent,
    resolve: {
      boek: BoekMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Boeks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BoekMySuffixUpdateComponent,
    resolve: {
      boek: BoekMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Boeks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const boekPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BoekMySuffixDeletePopupComponent,
    resolve: {
      boek: BoekMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Boeks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
