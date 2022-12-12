class ResepModel {
  String idResep;
  String dokter;
  String pasien;
  String apoteker;
  String status;
  List<dynamic> listObat;

  ResepModel(
      this.idResep,
      this.dokter,
      this.pasien,
      this.status,
      this.apoteker,
      this.listObat
      );
}
