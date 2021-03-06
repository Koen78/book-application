import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IPersoonMySuffix, PersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';
import { PersoonMySuffixService } from './persoon-my-suffix.service';
import { IBoekMySuffix } from 'app/shared/model/boek-my-suffix.model';
import { BoekMySuffixService } from 'app/entities/boek-my-suffix/boek-my-suffix.service';

@Component({
  selector: 'jhi-persoon-my-suffix-update',
  templateUrl: './persoon-my-suffix-update.component.html'
})
export class PersoonMySuffixUpdateComponent implements OnInit {
  isSaving: boolean;

  boeks: IBoekMySuffix[];

  editForm = this.fb.group({
    id: [],
    naam: [],
    voornaam: [],
    wenslijstId: [],
    boekenlijstId: [],
    gelezenId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected persoonService: PersoonMySuffixService,
    protected boekService: BoekMySuffixService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ persoon }) => {
      this.updateForm(persoon);
    });
    this.boekService
      .query()
      .subscribe((res: HttpResponse<IBoekMySuffix[]>) => (this.boeks = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(persoon: IPersoonMySuffix) {
    this.editForm.patchValue({
      id: persoon.id,
      naam: persoon.naam,
      voornaam: persoon.voornaam,
      wenslijstId: persoon.wenslijstId,
      boekenlijstId: persoon.boekenlijstId,
      gelezenId: persoon.gelezenId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const persoon = this.createFromForm();
    if (persoon.id !== undefined) {
      this.subscribeToSaveResponse(this.persoonService.update(persoon));
    } else {
      this.subscribeToSaveResponse(this.persoonService.create(persoon));
    }
  }

  private createFromForm(): IPersoonMySuffix {
    return {
      ...new PersoonMySuffix(),
      id: this.editForm.get(['id']).value,
      naam: this.editForm.get(['naam']).value,
      voornaam: this.editForm.get(['voornaam']).value,
      wenslijstId: this.editForm.get(['wenslijstId']).value,
      boekenlijstId: this.editForm.get(['boekenlijstId']).value,
      gelezenId: this.editForm.get(['gelezenId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersoonMySuffix>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackBoekById(index: number, item: IBoekMySuffix) {
    return item.id;
  }
}
