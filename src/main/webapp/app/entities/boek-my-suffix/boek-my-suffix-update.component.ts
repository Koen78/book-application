import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBoekMySuffix, BoekMySuffix } from 'app/shared/model/boek-my-suffix.model';
import { BoekMySuffixService } from './boek-my-suffix.service';

@Component({
  selector: 'jhi-boek-my-suffix-update',
  templateUrl: './boek-my-suffix-update.component.html'
})
export class BoekMySuffixUpdateComponent implements OnInit {
  boek: IBoekMySuffix;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    titel: [],
    auteur: [],
    paginas: [],
    korteInhoud: []
  });

  constructor(protected boekService: BoekMySuffixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ boek }) => {
      this.updateForm(boek);
      this.boek = boek;
    });
  }

  updateForm(boek: IBoekMySuffix) {
    this.editForm.patchValue({
      id: boek.id,
      titel: boek.titel,
      auteur: boek.auteur,
      paginas: boek.paginas,
      korteInhoud: boek.korteInhoud
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const boek = this.createFromForm();
    if (boek.id !== undefined) {
      this.subscribeToSaveResponse(this.boekService.update(boek));
    } else {
      this.subscribeToSaveResponse(this.boekService.create(boek));
    }
  }

  private createFromForm(): IBoekMySuffix {
    const entity = {
      ...new BoekMySuffix(),
      id: this.editForm.get(['id']).value,
      titel: this.editForm.get(['titel']).value,
      auteur: this.editForm.get(['auteur']).value,
      paginas: this.editForm.get(['paginas']).value,
      korteInhoud: this.editForm.get(['korteInhoud']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBoekMySuffix>>) {
    result.subscribe((res: HttpResponse<IBoekMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
