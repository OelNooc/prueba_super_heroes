package cl.nooc.superheroes.viewmodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import cl.nooc.superheroes.modelo.SuperRespuestaItem;

@RunWith(MockitoJUnitRunner.class)
public class HeroViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<SuperRespuestaItem>> listaObserver;
    @Mock
    private Observer<SuperRespuestaItem> superRespuestaItemObserver;

    private HeroViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        viewModel = new HeroViewModel();
        viewModel.getRespuesta().observeForever(listaObserver);
        viewModel.getDetalle().observeForever(superRespuestaItemObserver);

    }

    @Test
    public void llamarApiTest() throws InterruptedException {

        viewModel.llamarApi();
        Thread.sleep(4000);
        List<SuperRespuestaItem> lista = viewModel.getRespuesta().getValue();
        verify(listaObserver).onChanged(viewModel.getRespuesta().getValue());

    }

    @Test
    public void obtenerDetalleTest() throws InterruptedException {

        viewModel.llamarApi();
        Thread.sleep(4000);
        List<SuperRespuestaItem> lista = viewModel.getRespuesta().getValue();
        viewModel.obtenerDetalle(lista.get(1));
        verify(superRespuestaItemObserver).onChanged(viewModel.getDetalle().getValue());

    }

}