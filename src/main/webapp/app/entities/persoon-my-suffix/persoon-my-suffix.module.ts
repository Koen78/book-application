import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookApplicationSharedModule } from 'app/shared/shared.module';
import { PersoonMySuffixComponent } from './persoon-my-suffix.component';
import { PersoonMySuffixDetailComponent } from './persoon-my-suffix-detail.component';
import { PersoonMySuffixUpdateComponent } from './persoon-my-suffix-update.component';
import { PersoonMySuffixDeletePopupComponent, PersoonMySuffixDeleteDialogComponent } from './persoon-my-suffix-delete-dialog.component';
import { persoonRoute, persoonPopupRoute } from './persoon-my-suffix.route';

const ENTITY_STATES = [...persoonRoute, ...persoonPopupRoute];

@NgModule({
  imports: [BookApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PersoonMySuffixComponent,
    PersoonMySuffixDetailComponent,
    PersoonMySuffixUpdateComponent,
    PersoonMySuffixDeleteDialogComponent,
    PersoonMySuffixDeletePopupComponent
  ],
  entryComponents: [PersoonMySuffixDeleteDialogComponent]
})
export class BookApplicationPersoonMySuffixModule {}
