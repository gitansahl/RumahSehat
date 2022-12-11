class DetailResepDTO {
  String idResep;
  String dokter;
  String pasien;
  String apoteker;
  String status;
  List<dynamic> listObat;

  DetailResepDTO(
      this.idResep,
      this.dokter,
      this.pasien,
      this.status,
      this.apoteker,
      this.listObat
      );
}
