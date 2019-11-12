import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookApplicationTestModule } from '../../../test.module';
import { PersoonMySuffixDetailComponent } from 'app/entities/persoon-my-suffix/persoon-my-suffix-detail.component';
import { PersoonMySuffix } from 'app/shared/model/persoon-my-suffix.model';

describe('Component Tests', () => {
  describe('PersoonMySuffix Management Detail Component', () => {
    let comp: PersoonMySuffixDetailComponent;
    let fixture: ComponentFixture<PersoonMySuffixDetailComponent>;
    const route = ({ data: of({ persoon: new PersoonMySuffix(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BookApplicationTestModule],
        declarations: [PersoonMySuffixDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PersoonMySuffixDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersoonMySuffixDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.persoon).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
