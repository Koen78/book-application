import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';
import { PersoonMySuffixService } from './persoon-my-suffix.service';

@Component({
  selector: 'jhi-persoon-my-suffix-delete-dialog',
  templateUrl: './persoon-my-suffix-delete-dialog.component.html'
})
export class PersoonMySuffixDeleteDialogComponent {
  persoon: IPersoonMySuffix;

  constructor(
    protected persoonService: PersoonMySuffixService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.persoonService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'persoonListModification',
        content: 'Deleted an persoon'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-persoon-my-suffix-delete-popup',
  template: ''
})
export class PersoonMySuffixDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ persoon }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PersoonMySuffixDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.persoon = persoon;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/persoon-my-suffix', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/persoon-my-suffix', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
