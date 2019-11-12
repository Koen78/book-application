import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';
import { AccountService } from 'app/core';
import { PersoonMySuffixService } from './persoon-my-suffix.service';

@Component({
  selector: 'jhi-persoon-my-suffix',
  templateUrl: './persoon-my-suffix.component.html'
})
export class PersoonMySuffixComponent implements OnInit, OnDestroy {
  persoons: IPersoonMySuffix[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected persoonService: PersoonMySuffixService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected activatedRoute: ActivatedRoute,
    protected accountService: AccountService
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ? this.activatedRoute.snapshot.params['search'] : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.persoonService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IPersoonMySuffix[]>) => res.ok),
          map((res: HttpResponse<IPersoonMySuffix[]>) => res.body)
        )
        .subscribe((res: IPersoonMySuffix[]) => (this.persoons = res), (res: HttpErrorResponse) => this.onError(res.message));
      return;
    }
    this.persoonService
      .query()
      .pipe(
        filter((res: HttpResponse<IPersoonMySuffix[]>) => res.ok),
        map((res: HttpResponse<IPersoonMySuffix[]>) => res.body)
      )
      .subscribe(
        (res: IPersoonMySuffix[]) => {
          this.persoons = res;
          this.currentSearch = '';
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.currentSearch = query;
    this.loadAll();
  }

  clear() {
    this.currentSearch = '';
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPersoons();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPersoonMySuffix) {
    return item.id;
  }

  registerChangeInPersoons() {
    this.eventSubscriber = this.eventManager.subscribe('persoonListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
