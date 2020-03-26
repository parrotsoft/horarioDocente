/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.TipoDocumento;

/**
 *
 * @author Miguel
 */
public interface ITipoDocumentoDao {
    public boolean save(TipoDocumento data);
    public List<TipoDocumento> list();
    public boolean update(TipoDocumento data);
    public boolean delete(TipoDocumento data);     
}
