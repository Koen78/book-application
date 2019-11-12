import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookApplicationSharedModule } from 'app/shared/shared.module';
import { BoekMySuffixComponent } from './boek-my-suffix.component';
import { BoekMySuffixDetailComponent } from './boek-my-suffix-detail.component';
import { BoekMySuffixUpdateComponent } from './boek-my-suffix-update.component';
import { BoekMySuffixDeletePopupComponent, BoekMySuffixDeleteDialogComponent } from './boek-my-suffix-delete-dialog.component';
import { boekRoute, boekPopupRoute } from './boek-my-suffix.route';

const ENTITY_STATES = [...boekRoute, ...boekPopupRoute];

@NgModule({
  imports: [BookApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BoekMySuffixComponent,
    BoekMySuffixDetailComponent,
    BoekMySuffixUpdateComponent,
    BoekMySuffixDeleteDialogComponent,
    BoekMySuffixDeletePopupComponent
  ],
  entryComponents: [BoekMySuffixDeleteDialogComponent]
})
export class BookApplicationBoekMySuffixModule {}
