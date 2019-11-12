import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PersoonComponentsPage, PersoonDeleteDialog, PersoonUpdatePage } from './persoon-my-suffix.page-object';

const expect = chai.expect;

describe('Persoon e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let persoonComponentsPage: PersoonComponentsPage;
  let persoonUpdatePage: PersoonUpdatePage;
  let persoonDeleteDialog: PersoonDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Persoons', async () => {
    await navBarPage.goToEntity('persoon-my-suffix');
    persoonComponentsPage = new PersoonComponentsPage();
    await browser.wait(ec.visibilityOf(persoonComponentsPage.title), 5000);
    expect(await persoonComponentsPage.getTitle()).to.eq('Persoons');
  });

  it('should load create Persoon page', async () => {
    await persoonComponentsPage.clickOnCreateButton();
    persoonUpdatePage = new PersoonUpdatePage();
    expect(await persoonUpdatePage.getPageTitle()).to.eq('Create or edit a Persoon');
    await persoonUpdatePage.cancel();
  });

  it('should create and save Persoons', async () => {
    const nbButtonsBeforeCreate = await persoonComponentsPage.countDeleteButtons();

    await persoonComponentsPage.clickOnCreateButton();
    await promise.all([
      persoonUpdatePage.setNaamInput('naam'),
      persoonUpdatePage.setVoornaamInput('voornaam'),
      persoonUpdatePage.wenslijstSelectLastOption(),
      persoonUpdatePage.boekenlijstSelectLastOption(),
      persoonUpdatePage.gelezenSelectLastOption()
    ]);
    expect(await persoonUpdatePage.getNaamInput()).to.eq('naam', 'Expected Naam value to be equals to naam');
    expect(await persoonUpdatePage.getVoornaamInput()).to.eq('voornaam', 'Expected Voornaam value to be equals to voornaam');
    await persoonUpdatePage.save();
    expect(await persoonUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await persoonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Persoon', async () => {
    const nbButtonsBeforeDelete = await persoonComponentsPage.countDeleteButtons();
    await persoonComponentsPage.clickOnLastDeleteButton();

    persoonDeleteDialog = new PersoonDeleteDialog();
    expect(await persoonDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Persoon?');
    await persoonDeleteDialog.clickOnConfirmButton();

    expect(await persoonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
