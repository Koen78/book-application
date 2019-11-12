import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BoekComponentsPage, BoekDeleteDialog, BoekUpdatePage } from './boek-my-suffix.page-object';

const expect = chai.expect;

describe('Boek e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let boekComponentsPage: BoekComponentsPage;
  let boekUpdatePage: BoekUpdatePage;
  let boekDeleteDialog: BoekDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Boeks', async () => {
    await navBarPage.goToEntity('boek-my-suffix');
    boekComponentsPage = new BoekComponentsPage();
    await browser.wait(ec.visibilityOf(boekComponentsPage.title), 5000);
    expect(await boekComponentsPage.getTitle()).to.eq('Boeks');
  });

  it('should load create Boek page', async () => {
    await boekComponentsPage.clickOnCreateButton();
    boekUpdatePage = new BoekUpdatePage();
    expect(await boekUpdatePage.getPageTitle()).to.eq('Create or edit a Boek');
    await boekUpdatePage.cancel();
  });

  it('should create and save Boeks', async () => {
    const nbButtonsBeforeCreate = await boekComponentsPage.countDeleteButtons();

    await boekComponentsPage.clickOnCreateButton();
    await promise.all([
      boekUpdatePage.setTitelInput('titel'),
      boekUpdatePage.setAuteurInput('auteur'),
      boekUpdatePage.setPaginasInput('5'),
      boekUpdatePage.setKorteInhoudInput('korteInhoud')
    ]);
    expect(await boekUpdatePage.getTitelInput()).to.eq('titel', 'Expected Titel value to be equals to titel');
    expect(await boekUpdatePage.getAuteurInput()).to.eq('auteur', 'Expected Auteur value to be equals to auteur');
    expect(await boekUpdatePage.getPaginasInput()).to.eq('5', 'Expected paginas value to be equals to 5');
    expect(await boekUpdatePage.getKorteInhoudInput()).to.eq('korteInhoud', 'Expected KorteInhoud value to be equals to korteInhoud');
    await boekUpdatePage.save();
    expect(await boekUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await boekComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Boek', async () => {
    const nbButtonsBeforeDelete = await boekComponentsPage.countDeleteButtons();
    await boekComponentsPage.clickOnLastDeleteButton();

    boekDeleteDialog = new BoekDeleteDialog();
    expect(await boekDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Boek?');
    await boekDeleteDialog.clickOnConfirmButton();

    expect(await boekComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
