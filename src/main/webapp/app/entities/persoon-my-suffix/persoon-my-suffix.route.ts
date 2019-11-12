import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';
import { PersoonMySuffixService } from './persoon-my-suffix.service';
import { PersoonMySuffixComponent } from './persoon-my-suffix.component';
import { PersoonMySuffixDetailComponent } from './persoon-my-suffix-detail.component';
import { PersoonMySuffixUpdateComponent } from './persoon-my-suffix-update.component';
import { PersoonMySuffixDeletePopupComponent } from './persoon-my-suffix-delete-dialog.component';
import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class PersoonMySuffixResolve implements Resolve<IPersoonMySuffix> {
  constructor(private service: PersoonMySuffixService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersoonMySuffix> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((persoon: HttpResponse<PersoonMySuffix>) => persoon.body));
    }
    return of(new PersoonMySuffix());
  }
}

export const persoonRoute: Routes = [
  {
    path: '',
    component: PersoonMySuffixComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Persoons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PersoonMySuffixDetailComponent,
    resolve: {
      persoon: PersoonMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Persoons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PersoonMySuffixUpdateComponent,
    resolve: {
      persoon: PersoonMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Persoons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PersoonMySuffixUpdateComponent,
    resolve: {
      persoon: PersoonMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Persoons'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const persoonPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PersoonMySuffixDeletePopupComponent,
    resolve: {
      persoon: PersoonMySuffixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Persoons'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
